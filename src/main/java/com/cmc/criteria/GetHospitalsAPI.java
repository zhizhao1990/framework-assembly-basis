/**
 * 获取医院列表
 * @author HT-LiChuanbin
 * @version 2017年3月8日 上午11:57:56

public class GetHospitalsAPI extends AbstractService {

    private static final Logger LOG = LoggerFactory.getLogger(GetHospitalsAPI.class);

    private HospitalService hospitalService;

    public void setHospitalService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @Override
    public JSONObject service(JSONObject req) {
        try {
            long pageNo = Long.parseLong(req.optString("page_no"));
            long pageSize = Long.parseLong(req.optString("page_size"));
            String key = req.optString("key");
            
            PaginationResultWX<HospitalModel> pmHospitals = hospitalService.getHospitals(pageNo, pageSize, key);
            
            JSONObject resData = new JSONObject();
            resData.put("page_no", pmHospitals.getPageNo());
            resData.put("page_count", pmHospitals.getPageCount());
            resData.put("total_count", pmHospitals.getTotalCount());
            resData.put("hospitals", this.getHospitals(pmHospitals.getList()));

            return super.success(resData, LOG);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return super.fail(ApiResCode.SERVER_ERROR.getRetCode(), ApiResCode.SERVER_ERROR.getDesc(), null, LOG, e);
        }
    }

    /* 组装医院信息
    private JSONArray getHospitals(List<HospitalModel> mHospitals) {
        JSONArray jaHospitals = new JSONArray();
        Iterator<HospitalModel> imFaculties = mHospitals.iterator();
        while (imFaculties.hasNext()) {
            HospitalModel mHospital = imFaculties.next();
            JSONObject jHospital = new JSONObject();
            jHospital.put("hospital_no", mHospital.getHospitalNo());
            jHospital.put("hospital_name", mHospital.getName());
            jaHospitals.add(jHospital);
        }
        return jaHospitals;
    }

}
*/

/*
 /**
  * 获取医院列表
  * @author HT-LiChuanbin
  * @version 2017年3月8日 下午12:01:12
  * @param pageNo 页码
  * @param pageSize 分页大小
  * @param key 查询条件
  * @return 医院列表分页数据
    public PaginationResultWX<HospitalModel> getHospitals(long pageNo, long pageSize, String key);
 */

/* 
 * @Override
    public PaginationResultWX<HospitalModel> getHospitals(long pageNo, long pageSize, String key) {
        key = key.trim();
        long totalCount = hospitalMapper.selectCount(key);
        long pageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;

        long start = (pageNo - 1) * pageSize;
        long offset = pageSize;
        List<Hospital> hospitals = hospitalMapper.select(start, offset, key);
        List<HospitalModel> mHospitals = ModelDataObjectUtil.doList2modelList(hospitals, HospitalModel.class);

        PaginationResultWX<HospitalModel> pmHospitals = new PaginationResultWX<HospitalModel>();
        pmHospitals.setPageNo(pageNo);
        pmHospitals.setPageCount(pageCount);
        pmHospitals.setTotalCount(totalCount);
        pmHospitals.setList(mHospitals);
        return pmHospitals;
    }
 */
/*
@Select({
        "SELECT COUNT(*)",
        "FROM hospital",
        "WHERE is_delete = '1'",
        "AND `name` LIKE '%' #{key, jdbcType=VARCHAR} '%'"
    })
    long selectCount(@Param("key") String key);
 */