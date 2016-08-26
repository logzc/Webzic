package com.logzc.webzic.orm.kpi;

import com.logzc.webzic.orm.table.Table;

import java.util.Date;

/**
 * Created by lishuang on 2016/8/22.
 */
@Table(name = "kpi_history_by_feeder")
public class KpiHistoryByFeeder{

    public String utility;
    public String substationRegion;
    public String feeder;
    public Date date;
    public long nAssets;
    public long nLaterals;
    public long nTransformers;
    public long nMeters;
    public long nAnalyzedAssets;
    public long nAnalyzedLaterals;
    public long nAnalyzedTransformers;
    public long nAnalyzedMeters;
    public long nErrorAssets;
    public long nErrorLaterals;
    public long nErrorTransformers;
    public long nErrorMeters;
    public long nPhaseError;
    public long nConnectivityError;
    public float avgPhaseConfidence;
    public float avgConnectivityConfidence;


}
