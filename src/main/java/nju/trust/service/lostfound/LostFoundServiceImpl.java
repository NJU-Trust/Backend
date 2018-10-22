package nju.trust.service.lostfound;

import nju.trust.dao.lostfound.LostAndFoundRepository;
import nju.trust.dao.lostfound.LostAndFoundSpecification;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.lostfound.LostAndFound;
import nju.trust.entity.user.User;
import nju.trust.exception.ResourceNotFoundException;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: 161250127
 * @Description:
 * @Date: 2018/10/20
 */
@Service
public class LostFoundServiceImpl implements LostAndFoundService {

    private UserRepository userRepository;
    private LostAndFoundRepository lostAndFoundRepository;
    @Autowired
    public LostFoundServiceImpl(UserRepository userRepository, LostAndFoundRepository lostAndFoundRepository) {
        this.userRepository = userRepository;
        this.lostAndFoundRepository = lostAndFoundRepository;
    }


    @Override
    public ApiResponse launchTask(UploadLostAndFoundRequest uploadLostAndFoundRequest, String username) {
        TaskInfo taskInfo = new TaskInfo(uploadLostAndFoundRequest);
        taskInfo.setDate(LocalDate.now());
        taskInfo.setState(ProcessState.DOING.getStr());
        taskInfo.setUsername(username);
        LostAndFound lostAndFound = new LostAndFound(taskInfo, username);
        lostAndFoundRepository.save(lostAndFound);
        return new ApiResponse(true, "发布成功");
    }

    @Override
    public List<TaskInfo> getMyTask(String username, MsgProperty msgProperty, ProcessState processState) {
        List<LostAndFound> lostAndFounds = lostAndFoundRepository.findDistinctByUsernameAndPropertyAndState(username,msgProperty,processState);
        List<TaskInfo> taskInfos = new ArrayList<>();
        for(int i=0;i<lostAndFounds.size();i++){
            taskInfos.add(new TaskInfo(lostAndFounds.get(i)));
        }
        return taskInfos;
    }

    @Override
    public TaskInfoPage findTask(LostAndFoundFilterRequest filter) {
        Specification<LostAndFound> specification = new LostAndFoundSpecification(filter);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getSize(),new Sort("ASC".equals(filter.getSort()) ?Sort.Direction.ASC:Sort.Direction.DESC, filter.getProperties()));
        Page<LostAndFound> trades = lostAndFoundRepository.findAll(specification, pageable);
        return new TaskInfoPage(trades.getTotalPages(), trades.stream().map(TaskInfo::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse submitResult(long taskID, String involvedPerson) {
        LostAndFound lostAndFound = lostAndFoundRepository.findById(taskID).get();
        userRepository.findByUsername(involvedPerson).orElseThrow(
                () -> new ResourceNotFoundException("username not found")
        );


        lostAndFound.setToUsername(involvedPerson);
        lostAndFound.setState(ProcessState.DONE);
        lostAndFoundRepository.save(lostAndFound);
        return new ApiResponse(true, "成功");
    }
}
