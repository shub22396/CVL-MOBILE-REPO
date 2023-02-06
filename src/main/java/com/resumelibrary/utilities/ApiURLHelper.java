package com.resumelibrary.utilities;

import static com.resumelibrary.utilities.WebURLHelper.getParameterFromEnvOrSysParam;

public class ApiURLHelper {

    static String candidateRegistration = "/v1/candidate/registration";
    static String candidateBackfillRegistration = "/v1/candidate/backfill/register";
    static String candidateBackfillUpdate = "/v1/candidate/backfill/update";
    static String candidateBackfillDelete = "/v1/candidate/backfill/delete";
    static String candidateBackfillDeletedCandidates = "/v1/candidate/backfill/deleted-candidates";
    static String candidateBackfillDuplicateCandidates = "/v1/candidate/backfill/duplicate-candidates";
    static String candidateBackfillUnlock = "/v1/candidate/backfill/unlock/";
    static String candidateMd5Check = "/v1/candidate/md5check/";
    static String candidateBackfillCreditsRemaining = "/v1/candidate/backfill/creditsremaining";
    static String candidateUnlockedCandidates = "/v1/candidate/unlocked-candidates";
    static String candidateBackfillStats = "/v1/candidate/backfill/stats/";
    static String candidateBackfillSearch = "/v1/candidate/backfill/search";
    static String candidateBackfillView = "/v1/candidate/backfill/view/";
    static String candidateBackfillEmail = "/v1/candidate/backfill/email";
    static String jobCreatePost = "/v2/job/create";
    static String jobUpdate = "/v2/job/update/";
    static String jobSearch = "/v2/job/search";
    static String jobDelete = "/v2/job/delete/";
    static String isJobActive = "/v2/job/is_active/";
    static String jobAlert = "/v2/job/search_job_alert";
    static String activeJobCount = "/v2/job/active_jobs_count";
    static String candidateSendFeedback = "/v1/send-feedback";
    static String candidateApply = "/v1/apply/";
    static String emailBounce = "/v1/email/bounce";
    static String locationSearch = "/v1/location/search";
    static String icimsLogin = "/partners/icims/login";
    static String partnersEmailCheck = "/partners_internal/check_email";
    static String jobCredit = "/job-credit/spend";

    /**
     * Prepare host name using env or system property or default values.     *
     * @return String
     */
    private static String getApiHostName() {
        String apiHostName = null;

        if (WebURLHelper.isParameterSetInEnvOrParam("BRANCH")) {
            String branchName = getParameterFromEnvOrSysParam("BRANCH", "master");

            // we get branch name in form of: origin/<branch_name> so we need to split at last /
            branchName = branchName.substring(branchName.lastIndexOf("/") + 1);

            // create host name from branch name
            apiHostName = "https://api-" + branchName + ".ci.resume-library.com";
        }

        System.out.println("API URL is : " + apiHostName);

        return apiHostName;
    }

    public static String getApiUrl() {
        String apiTestUrl = null;
        if (getParameterFromEnvOrSysParam("USER", "jenkins").equals("jenkins")) {
            apiTestUrl = getApiHostName();
        } else {
            apiTestUrl = DataHelper.getApiUrl();
        }
        return apiTestUrl;
    }

    public static String getCandidateRegistration() {
        return candidateRegistration;
    }

    public static String getCandidateBackfillRegistration() {
        return candidateBackfillRegistration;
    }

    public static String getCandidateBackfillUpdate() {
        return candidateBackfillUpdate;
    }

    public static String getCandidateBackfillDelete() {
        return candidateBackfillDelete;
    }

    public static String getCandidateBackfillDeletedCandidates() {
        return candidateBackfillDeletedCandidates;
    }

    public static String getCandidateBackfillDuplicateCandidates() {
        return candidateBackfillDuplicateCandidates;
    }

    public static String getJobCreatePost() {
        return jobCreatePost;
    }

    public static String getCandidateBackfillUnlock() {
        return candidateBackfillUnlock;
    }

    public static String getCandidateMd5Check() {
        return candidateMd5Check;
    }

    public static String getCandidateBackfillCreditsRemaining() {
        return candidateBackfillCreditsRemaining;
    }

    public static String getCandidateUnlockedCandidates() {
        return candidateUnlockedCandidates;
    }

    public static String getCandidateBackfillStats() {
        return candidateBackfillStats;
    }

    public static String getCandidateBackfillSearch() {
        return candidateBackfillSearch;
    }

    public static String getCandidateBackfillView() {
        return candidateBackfillView;
    }

    public static String getCandidateSendFeedback() {
        return candidateSendFeedback;
    }

    public static String getCandidateApply() {
        return candidateApply;
    }

    public static String getJobSearch() {
        return jobSearch;
    }

    public static String getActiveJobCount() {
        return activeJobCount;
    }

    public static String getJobAlert() {
        return jobAlert;
    }

    public static String getJobUpdate() {
        return jobUpdate;
    }

    public static String getJobDelete() {
        return jobDelete;
    }

    public static String getCandidateBackfillEmail() { return candidateBackfillEmail;}

    public static String getEmailBounce() {return emailBounce;}

    public static String getLocationSearch() {return locationSearch;}

    public static String getIsJobActive() {return isJobActive;}

    public static String getIcimsLogin(){return icimsLogin;}

    public static String getPartnersEmailCheck(){return partnersEmailCheck;}

    public static String getJobCredit() {return jobCredit;}
}