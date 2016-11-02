package com.arjun.bean;
/**
 * 
 * @author ARJUN SINGH
 *
 */
public class Validator {
    private boolean statusFlag;
    private APIResponse apiResponse;

    public boolean isStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(boolean statusFlag) {
        this.statusFlag = statusFlag;
    }

    public APIResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(APIResponse apiResponse) {
        this.apiResponse = apiResponse;
    }


}
