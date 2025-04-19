package br.com.administrator.mappers;

import org.modelmapper.ModelMapper;

import br.com.administrator.to.TODevice;
import br.com.fitnesspro.shared.communication.dtos.serviceauth.DeviceDTO;

public final class DeviceMapper {

	private ModelMapper modelMapper = new ModelMapper();
	
	public DeviceDTO getDeviceDTOFrom(TODevice toDevice) {
		return modelMapper.map(toDevice, DeviceDTO.class);
	}
	
	public TODevice getTODeviceFrom(DeviceDTO deviceDTO) {
		return modelMapper.map(deviceDTO, TODevice.class);
	}
}
