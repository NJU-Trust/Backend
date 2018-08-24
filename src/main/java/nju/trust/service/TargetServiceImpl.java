package nju.trust.service;

import nju.trust.dao.TargetRepository;
import nju.trust.entity.target.BaseTarget;
import nju.trust.entity.target.LargeTarget;
import nju.trust.entity.target.SmallTarget;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.target.BasicTargetRequest;
import nju.trust.payloads.target.LargeTargetRequest;
import nju.trust.payloads.target.SmallTargetRequest;
import nju.trust.payloads.target.TargetInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Author: J.D. Liao
 * Date: 2018/8/24
 * Description:
 */
@Service
public class TargetServiceImpl implements TargetService {

    private static final Logger log = LoggerFactory.getLogger("TargetService");

    private TargetRepository targetRepository;

    public TargetServiceImpl(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public TargetInfo getTargetInfo(Long targetId) {
        return null;
    }

    @Override
    public ApiResponse applySmallTarget(SmallTargetRequest request, String username) {
        SmallTarget smallTarget = new SmallTarget(request, username);
        return setFileAndSaveTarget(smallTarget, request);
    }

    @Override
    public ApiResponse applyLargeTarget(LargeTargetRequest request, String username) {
        LargeTarget largeTarget = new LargeTarget(request, username);
        return setFileAndSaveTarget(largeTarget, request);
    }

    @Override
    public ApiResponse investTarget(Long targetId, String username) {
        return null;
    }

    @Override
    public ApiResponse schoolFellowInvestTarget(Long targetId, String username, String interestPlan) {
        return null;
    }

    private ApiResponse setFileAndSaveTarget(BaseTarget target, BasicTargetRequest request) {
        try {
            target.setFiles(request.convertFileToByte());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error occurs when getting bytes from MultiPartFile");
            return ApiResponse.serverGoseWrong();
        }

        targetRepository.save(target);

        // TODO: 2018/8/24 通知审批程序
        return ApiResponse.successResponse();
    }
}
