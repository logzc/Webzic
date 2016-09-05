package com.logzc.webzic.orm.kpi.model;

import com.logzc.webzic.orm.table.Table;

/**
 * Created by lishuang on 2016/8/26.
 */
@Table(name = "cmodel.lateral")
public class Lateral {

    public String utility;
    public String feeder;
    public String lateral;
    public String substation_region;
    public String geometry;
    public String phase;
    public String ou;
    public boolean analyzed;
    public boolean error;
    public boolean pError;
    public boolean cError;
    public long nTransformers;
    public long nMeters;
    public long nErrorTransformers;
    public long nErrorMeters;
    public long totalChildrenError;
}
