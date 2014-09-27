package kr.co.hanalee.model;

import kr.co.hanalee.util.DPIName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hana Lee on 2014. 9. 28..
 */
public class TVDPI extends DPI {

	private static final long serialVersionUID = -5857030313502416095L;

	public TVDPI() {
		super(DPIName.tvdpi);
	}

	/**
	 * 하위 클래스들은 getBasicNumbersForCalculation 을 구현하여
	 * 각 원본 DPI 들이 하위 DPI 들을 위한 각 DPI 만의 계산값들을 반환해야 한다.
	 *
	 * @return 하위 DPI 들의 이름과 계산을 위한 기본 값들을 <code>Map</code>에 담아 반환한다.
	 */
	@Override
	public Map<DPIName, Double> getBasicNumbersForCalculation() {
		Map<DPIName, Double> numbers = new HashMap<DPIName, Double>();
		numbers.put(DPIName.ldpi, 1.773333333333333);

		return numbers;
	}
}
