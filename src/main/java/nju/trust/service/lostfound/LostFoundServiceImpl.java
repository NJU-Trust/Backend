package nju.trust.service.lostfound;

import nju.trust.dao.lostfound.LostAndFoundRepository;
import nju.trust.dao.user.UserRepository;
import nju.trust.entity.lostfound.LostAndFound;
import nju.trust.entity.user.User;
import nju.trust.payloads.ApiResponse;
import nju.trust.payloads.lostfound.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public ApiResponse launchTask(TaskInfo taskInfo) {
        taskInfo.setDate(LocalDate.now());
        taskInfo.setState(ProcessState.DOING);
        Optional<User> userList = userRepository.findByUsername(taskInfo.getUsername());
        User user = userList.get();
        LostAndFound lostAndFound = new LostAndFound(taskInfo,user);
        lostAndFoundRepository.save(lostAndFound);
        return null;
    }

    @Override
    public List<TaskInfo> getMyTask(MsgProperty msgProperty, ProcessState processState) {
        return null;
    }

    @Override
    public List<TaskInfo> findTask(MsgProperty msgProperty, ThingsType thingsType, LostPlace lostPlace) {
        return null;
    }
}
