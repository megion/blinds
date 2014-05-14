package com.megion.site.blinds.service;

import info.magnolia.module.blossom.dialog.TabBuilder;

import java.util.Collection;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.springframework.stereotype.Service;

import com.megion.site.core.model.datatype.DataType;
import com.megion.site.core.model.datatype.DataTypeValue;
import com.megion.site.core.provider.DataTypeProvider;

@Service
public class DataTypeProviderImpl implements DataTypeProvider {

	@Override
	public Collection<DataType> getAllDataType() {
		return null;
	}

	@Override
	public Map<String, DataType> getDataTypeMapByTreeName() {
		return null;
	}

	@Override
	public <T extends DataTypeValue> T createDataTypeValue(Node node,
			DataType dataType) throws RepositoryException {
		return null;
	}

	@Override
	public void addDataTypeDialogControls(TabBuilder tabBuilder,
			DataType dataType) throws RepositoryException {
	}

}