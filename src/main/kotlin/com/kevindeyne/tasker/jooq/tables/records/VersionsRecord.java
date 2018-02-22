/*
 * This file is generated by jOOQ.
*/
package com.kevindeyne.tasker.jooq.tables.records;


import com.kevindeyne.tasker.jooq.tables.Versions;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VersionsRecord extends UpdatableRecordImpl<VersionsRecord> implements Record6<Long, Long, Long, Integer, Integer, Integer> {

    private static final long serialVersionUID = -669631758;

    /**
     * Setter for <code>taskr.versions.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>taskr.versions.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>taskr.versions.project_id</code>.
     */
    public void setProjectId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>taskr.versions.project_id</code>.
     */
    public Long getProjectId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>taskr.versions.branch_id</code>.
     */
    public void setBranchId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>taskr.versions.branch_id</code>.
     */
    public Long getBranchId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>taskr.versions.major_version</code>.
     */
    public void setMajorVersion(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>taskr.versions.major_version</code>.
     */
    public Integer getMajorVersion() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>taskr.versions.minor_version</code>.
     */
    public void setMinorVersion(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>taskr.versions.minor_version</code>.
     */
    public Integer getMinorVersion() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>taskr.versions.patch_version</code>.
     */
    public void setPatchVersion(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>taskr.versions.patch_version</code>.
     */
    public Integer getPatchVersion() {
        return (Integer) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, Long, Long, Integer, Integer, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Long, Long, Long, Integer, Integer, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Versions.VERSIONS.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field2() {
        return Versions.VERSIONS.PROJECT_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return Versions.VERSIONS.BRANCH_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Versions.VERSIONS.MAJOR_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Versions.VERSIONS.MINOR_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Versions.VERSIONS.PATCH_VERSION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component2() {
        return getProjectId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getBranchId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component4() {
        return getMajorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component5() {
        return getMinorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component6() {
        return getPatchVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value2() {
        return getProjectId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getBranchId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getMajorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getMinorVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getPatchVersion();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value2(Long value) {
        setProjectId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value3(Long value) {
        setBranchId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value4(Integer value) {
        setMajorVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value5(Integer value) {
        setMinorVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord value6(Integer value) {
        setPatchVersion(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionsRecord values(Long value1, Long value2, Long value3, Integer value4, Integer value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached VersionsRecord
     */
    public VersionsRecord() {
        super(Versions.VERSIONS);
    }

    /**
     * Create a detached, initialised VersionsRecord
     */
    public VersionsRecord(Long id, Long projectId, Long branchId, Integer majorVersion, Integer minorVersion, Integer patchVersion) {
        super(Versions.VERSIONS);

        set(0, id);
        set(1, projectId);
        set(2, branchId);
        set(3, majorVersion);
        set(4, minorVersion);
        set(5, patchVersion);
    }
}