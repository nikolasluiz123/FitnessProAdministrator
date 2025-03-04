package br.com.administrator.managedbean.common.lazymodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import br.com.administrator.to.common.AbstractModelTO;

@SuppressWarnings("serial")
public abstract class AbstractLazyDataModel<T extends AbstractModelTO> extends LazyDataModel<T> {

	private List<T> data;
	private int first;
	private Map<String, SortMeta> sortBy;
	private Map<String, FilterMeta> filterBy;

	public AbstractLazyDataModel() {
		super();
		this.data = new ArrayList<>();
	}
	
	protected abstract List<T> onLoad(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy);
	
	@Override
	public List<T> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		this.first = first;
		this.sortBy = sortBy;
		this.filterBy = filterBy;
		
		List<T> result = onLoad(first, pageSize, sortBy, filterBy);
		this.data = result;
		
		return result;
	}
		
	@Override
	public T getRowData(String rowKey) {
		return this.data.stream().filter(isEqualsId(rowKey)).findFirst().orElse(null);
	}

	private Predicate<? super T> isEqualsId(String rowKey) {
		return x -> x.getId().equals(rowKey);
	}
	
	@Override
	public String getRowKey(T object) {
		return object.getId();
	}

	public void reloadPreservingPagingState() {
		load(this.first, getPageSize(), this.sortBy, this.filterBy);
	}
	
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
