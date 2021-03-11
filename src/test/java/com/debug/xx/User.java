package com.debug.xx;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Data
@AllArgsConstructor
public class User {

	private String id;

	@NotBlank
	@Size(max = 20)
	private String name;

	@AssertFalse(message = "必须为false")
	private Boolean test;

	@NotNull
	@Pattern(regexp = "[A-Z][a-z][0-9]")
	private String password;

	@NotNull
	private Integer age;

	@Max(10)
	@Min(1)
	private Integer level;

	@DecimalMax(value = "5", message = "1的最大值5!")
	private String stringMax;

	@DecimalMax(value = "5", message = "2的最大值5!")
	private String stringMax2;

	@Digits(integer = 1, fraction = 1, message = "1.整数位1, 小数位1")
	private String digits1;

	@Digits(integer = 1, fraction = 1, message = "2.整数位1, 小数位1")
	private String digits2;

	@Digits(integer = 1, fraction = 1, message = "3.整数位1, 小数位1")
	private String digits3;

	@Range(min = 1, max = 3, message = "1. (31,53)范围")
	private String range1;

	@Range(min = 1, max = 3, message = "2. (31,53)范围")
	private String range2;

	@Range(min = 1, max = 3, message = "1. (1,3)范围")
	private int range3;

	@Range(min = 1, max = 3, message = "2. (1,3)范围")
	private int range4;
}

class UserTest {

	private static Validator validator;

	@BeforeAll
	public static void setUpValidator() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void validatorTest() {
		User user = new User(null, "", null, "!@#$", null, 11, "2", "",
				"1.1", "21.1", "1.12", "*","@",2,6);

		// 验证所有bean的所有约束 时间相关类
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		// 验证单个属性
		Set<ConstraintViolation<User>> constraintViolations2 = validator.validateProperty(user, "name");
		// 检查给定类的单个属性是否可以成功验证
		Set<ConstraintViolation<User>> constraintViolations3 = validator.validateValue(User.class, "password", "sa!");

		constraintViolations.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
		System.out.println("----------------");
		constraintViolations2.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
		System.out.println("----------------");
		constraintViolations3.forEach(constraintViolation -> System.out.println(constraintViolation.getMessage()));
	}

}