package kr.co.hanalee;

import kr.co.hanalee.model.DPI;
import kr.co.hanalee.model.XXHDPI;
import kr.co.hanalee.util.DPICalculator;
import kr.co.hanalee.util.DPIName;
import org.junit.Test;

import java.awt.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DPICalculateTest {

	@Test
	public void basicDPICalculateTest() {
		Dimension originalDimension = new Dimension(140, 120);

		DPI xxhdpi = new XXHDPI();

		Dimension newXHDPIDimension = DPICalculator.calculateNewDimension(originalDimension,
				xxhdpi.getBasicNumbersForCalculation().get(DPIName.xhdpi), true);

		assertThat(newXHDPIDimension.getHeight(), is(80.0));
		assertThat(newXHDPIDimension.getWidth(), is(93.0));
	}
}
