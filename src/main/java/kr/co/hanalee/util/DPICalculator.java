package kr.co.hanalee.util;

import kr.co.hanalee.model.DPI;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *         <p>
 *         Created by Hana Lee on 2014. 9. 26..
 */
public class DPICalculator {

	public static Map<DPIName, Dimension> calculate(DPI dpi, Dimension original) {
		Map<DPIName, Dimension> newDimensions = new HashMap<DPIName, Dimension>();
		Map<DPIName, Double> basicNumbers = dpi.getBasicNumbersForCalculation();

		for (DPIName key : basicNumbers.keySet()) {
			double originalHeight = original.getHeight();
			double originalWidth = original.getWidth();

			double newHeight = originalHeight / basicNumbers.get(key);
			double newWidth = originalWidth / basicNumbers.get(key);

			Dimension newDimension = new Dimension();
			newDimension.setSize(newWidth, newHeight);

			newDimensions.put(key, newDimension);
		}
		return newDimensions;
	}

	public static Dimension calculateNewDimension(Dimension originalDimension,
	                                              double number, boolean div) {
		Double widthResult;
		Double heightResult;
		if (div) {
			widthResult = originalDimension.getWidth() / number;
			heightResult = originalDimension.getHeight() / number;
		} else {
			widthResult = originalDimension.getWidth() * number;
			heightResult = originalDimension.getHeight() * number;
		}
		Long lwResult = Math.round(widthResult);
		Long lhResult = Math.round(heightResult);

		Dimension newDimension = new Dimension();
		newDimension.setSize(lwResult, lhResult);
		return newDimension;
	}
}
