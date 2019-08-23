package springwork.customAnnotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {	
	
	public static final Pattern	VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", 
					Pattern.CASE_INSENSITIVE);
	
	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
	
	@Override
	public void initialize(EmailConstraint ec) {
		
	}
	
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		return (validate(arg0) && (arg0.length() > 8) && (arg0.length() < 14));
	}

}
