package nju.trust.entity.flea;

import java.util.List;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/9/2
 * @Todo:
 */
public class SecondComment extends Comment {
    public SecondComment() {
        super();
    }

    private List<Comment> list;

    public List<Comment> getList() {
        return list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }
}
