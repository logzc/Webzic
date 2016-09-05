package com.logzc.webzic.orm.kpi.model;

import com.logzc.webzic.orm.table.Table;

import java.util.Date;

/**
 * Created by lishuang on 2016/8/22.
 */
@Table(name = "cmodel.kpi_history_by_utility")
public class KpiHistoryByUtility {


    public String utility;
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

    //spell mistake in db.
    public float avgConnectivityConfindence;


}
