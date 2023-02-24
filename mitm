import asyncio
import logging
from asyncio.events import AbstractEventLoop

from mitmproxy import options, http, ctx
from mitmproxy.tools.dump import DumpMaster as Master

from proxy.core import ProxyServer as __ProxyServer, ProxyConfig

_logger = logging.getLogger(__name__)


class MitmEventListener:

    def on_connect(self, flow: http.HTTPFlow):
        pass

    def on_request(self, flow: http.HTTPFlow):
        pass

    def on_response(self, flow: http.HTTPFlow):
        pass


class MitmAddon:
    config: ProxyConfig
    master: Master
    event_listener: list[MitmEventListener]

    def __init__(self, config: ProxyConfig, master: Master, event_listener: list[MitmEventListener]) -> None:
        self.config = config
        self.master = master
        self.event_listener = event_listener

    def running(self):
        _logger.debug("Mitm代理服务 启动成功! 监听端口: %d", self.config.listen_port)

    def http_connect(self, flow: http.HTTPFlow):
        for listener in self.event_listener:
            listener.on_connect(flow)

    def request(self, flow: http.HTTPFlow):
        for listener in self.event_listener:
            listener.on_request(flow)

    def response(self, flow: http.HTTPFlow):
        """
        拥有完整返回的请求处理
        """
        for listener in self.event_listener:
            listener.on_response(flow)


class MitmServer(__ProxyServer):
    _master: Master
    _addon: MitmAddon
    _event_listener: list[MitmEventListener]
    _host: str
    _port: int
    _ssl: bool
    _mode: [str]
    _loop: AbstractEventLoop
    _task:asyncio.Task

    def __init__(self, host="0.0.0.0", port=0, ssl=True, mode=None, ) -> None:
        """
        参考文档: https://docs.mitmproxy.org/stable/concepts-options/
        :param mode  模式:  "regular" (HTTP), "transparent", "socks5", "reverse:SPEC", and "upstream:SPEC"
        :param proxyauth  代理身份验证, username:pass 的形式, "any" 表示接受任何用户/密码
        """
        super().__init__()
        if mode is None:
            mode = ["regular"]
        self._host = host
        self._port = port
        self._ssl = ssl
        self._mode = mode
        self._event_listener = []

    async def __run(self, ):
        _logger.debug("Mitm代理服务 参数构建, 地址: %s:%d", self._host, self._port)
        option = options.Options(
            listen_host=self._host,
            listen_port=self._port,
            ssl_insecure=self._ssl,
            mode=self._mode,
        )
        _logger.debug("Mitm代理服务 实例构建")
        self._master = Master(option)
        self._addon = MitmAddon(self.config(), self._master, self._event_listener)
        self._master.addons.add(self._addon)
        _logger.debug("Mitm代理服务 开始启动")
        await self._master.run()

    def start(self):
        self._loop = asyncio.new_event_loop()
        asyncio.set_event_loop(self._loop)
        self._task = self._loop.create_task(self.__run())
        self._loop.run_until_complete(self._task)
        pass

    def stop(self):
        _logger.debug("Mitm代理服务 关闭")
        self._master.shutdown()
        # self._master.running().close()
        # self._master.running().cr_frame.clear()
        # self._task.cancel()
        #self._loop.call_soon_threadsafe(self._loop.stop)
        pass

    def config(self) -> ProxyConfig:
        config = ProxyConfig()
        config.listen_host = self._host
        config.listen_port = self._port
        return config

    def register(self, listener: MitmEventListener):
        self._event_listener.append(listener)


if __name__ == '__main__':
    server = MitmServer(port=30230)
    server.start_async()
    print("debug line")
    server.stop()
    print("debug line")
    pass
