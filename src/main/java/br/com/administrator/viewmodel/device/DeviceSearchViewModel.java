package br.com.administrator.viewmodel.device;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;

import br.com.administrator.mappers.DeviceMapper;
import br.com.administrator.service.webclient.DeviceWebClient;
import br.com.administrator.to.TODevice;
import br.com.administrator.utils.PrimefacesFiltersUtil;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;
import br.com.fitnesspro.shared.communication.paging.CommonPageInfos;
import br.com.fitnesspro.shared.communication.query.enums.EnumDeviceFields;
import br.com.fitnesspro.shared.communication.query.filter.DeviceFilter;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

@Dependent
public class DeviceSearchViewModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final DeviceWebClient webClient;
	private final DeviceMapper mapper;

	@Inject
	public DeviceSearchViewModel(DeviceWebClient webClient, DeviceMapper mapper) {
        this.webClient = webClient;
        this.mapper = mapper;
	}
	
	public List<TODevice> getListDevice(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) throws Exception {
		CommonPageInfos pageInfos = new CommonPageInfos(pageSize, first / pageSize);
		DeviceFilter filter = getDeviceFilter(filterBy, sortBy);
		
		List<DeviceDTO> result = webClient.getListDevice(filter, pageInfos);
		
		return result.stream().map(d -> mapper.getTODeviceFrom(d)).toList();
	}

	public int getCountListDevice(Map<String, FilterMeta> filterBy) throws Exception {
		return webClient.getCountListDevice(getDeviceFilter(filterBy, null));
	}
	
	private DeviceFilter getDeviceFilter(Map<String, FilterMeta> filterBy, Map<String, SortMeta> sortBy) {
		DeviceFilter filter = new DeviceFilter();
		PrimefacesFiltersUtil filterUtils = new PrimefacesFiltersUtil(filterBy);

		if (filterBy.containsKey(EnumDeviceFields.MODEL.getFieldName())) {
			filter.setModel(filterUtils.getStringFilterValue(EnumDeviceFields.MODEL.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.ID.getFieldName())) {
			filter.setId(filterUtils.getStringFilterValue(EnumDeviceFields.ID.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.BRAND.getFieldName())) {
			filter.setBrand(filterUtils.getStringFilterValue(EnumDeviceFields.BRAND.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.ANDROID_VERSION.getFieldName())) {
			filter.setAndroidVersion(filterUtils.getStringFilterValue(EnumDeviceFields.ANDROID_VERSION.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.PERSON_NAME.getFieldName())) {
			filter.setPersonName(filterUtils.getStringFilterValue(EnumDeviceFields.PERSON_NAME.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.CREATION_DATE.getFieldName())) {
			filter.setCreationDate(filterUtils.getDateTimeRangeFilter(EnumDeviceFields.CREATION_DATE.getFieldName()));
		}
		
		if (filterBy.containsKey(EnumDeviceFields.UPDATE_DATE.getFieldName())) {
			filter.setCreationDate(filterUtils.getDateTimeRangeFilter(EnumDeviceFields.UPDATE_DATE.getFieldName()));
		}
		
		if (sortBy != null && !sortBy.values().isEmpty()) {
			filter.setSort(filterUtils.getSortFromFieldList(sortBy, EnumDeviceFields.getEntries()));
		}
		
		return filter;
	}
}
