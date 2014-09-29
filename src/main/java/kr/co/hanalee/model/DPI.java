package kr.co.hanalee.model;

import kr.co.hanalee.util.DPIName;

import java.io.Serializable;
import java.util.Map;

/**
 * @author <a href="mailto:voyaging@leehana.co.kr">Hana Lee</a>
 *
 * Created by Hana Lee on 2014. 9. 26..
 * <p/>
 * 현재 선택된 원본 DPI 를 위한 추상 클래스
 */
public abstract class DPI implements Serializable {
	private DPIName name;

	public DPI(DPIName name) {
		this.name = name;
	}

	public DPIName getName() {
		return this.name;
	}

	/**
	 * 하위 클래스들은 getBasicNumbersForCalculation 을 구현하여
	 * 각 원본 DPI 들이 하위 DPI 들을 위한 각 DPI 만의 계산값들을 반환해야 한다.
	 *
	 * @return 하위 DPI 들의 이름과 계산을 위한 기본 값들을 <code>Map</code>에 담아 반환한다.
	 */
	public abstract Map<DPIName, Double> getBasicNumbersForCalculation();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DPI)) return false;

		DPI dpi = (DPI) o;

		if (name != dpi.name) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
