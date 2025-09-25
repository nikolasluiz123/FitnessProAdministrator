package br.com.administrator.to;

import java.time.LocalDateTime;

import br.com.administrator.to.common.AbstractModelTO;

public class TOExecutionLogSubPackage extends AbstractModelTO {

    private static final long serialVersionUID = 1L;

    private String entityName;
    private String executionLogPackageId;
    private Integer insertedItemsCount;
    private Integer updatedItemsCount;
    private Integer allItemsCount;
    private Long kbSize;
    private LocalDateTime lastUpdateDate;

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getExecutionLogPackageId() {
        return executionLogPackageId;
    }

    public void setExecutionLogPackageId(String executionLogPackageId) {
        this.executionLogPackageId = executionLogPackageId;
    }

    public Integer getInsertedItemsCount() {
        return insertedItemsCount;
    }

    public void setInsertedItemsCount(Integer insertedItemsCount) {
        this.insertedItemsCount = insertedItemsCount;
    }

    public Integer getUpdatedItemsCount() {
        return updatedItemsCount;
    }

    public void setUpdatedItemsCount(Integer updatedItemsCount) {
        this.updatedItemsCount = updatedItemsCount;
    }

    public Integer getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(Integer allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public Long getKbSize() {
        return kbSize;
    }

    public void setKbSize(Long kbSize) {
        this.kbSize = kbSize;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}