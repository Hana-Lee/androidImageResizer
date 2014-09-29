package kr.co.hanalee.model;

import kr.co.hanalee.util.DPIName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *
 * Created by Hana Lee on 2014. 9. 28..
 */
public class HDPI extends DPI {

	private static final long serialVersionUID = -2213412714043283492L;

	public HDPI() {
		super(DPIName.hdpi);
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
		numbers.put(DPIName.mdpi, 1.5);
		numbers.put(DPIName.tvdpi, 1.12781954887218);
		numbers.put(DPIName.ldpi, 2.0);

		return numbers;
	}
}
