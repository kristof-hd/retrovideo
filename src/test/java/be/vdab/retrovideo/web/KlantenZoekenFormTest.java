package be.vdab.retrovideo.web;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class KlantenZoekenFormTest {
	private Validator validator;

	@Before
	public void before() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void familienaamBevatOk() {
		Set<ConstraintViolation<KlantenZoekenForm>> violations = validator.validateValue(KlantenZoekenForm.class,
				"familienaamBevat", "co");
		assertTrue(violations.isEmpty());
	}

	@Test
	public void vanMoetIngevuldZijn() {
		Set<ConstraintViolation<KlantenZoekenForm>> violations = validator.validateValue(KlantenZoekenForm.class,
				"familienaamBevat", null);
		assertEquals(1, violations.size());
		assertTrue(violations.iterator().next().getMessageTemplate().contains(".NotBlank."));
	}

}
