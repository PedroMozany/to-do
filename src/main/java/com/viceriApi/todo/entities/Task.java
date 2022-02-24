package com.viceriApi.todo.entities;

import com.viceriApi.todo.enums.PriorityTask;
import com.viceriApi.todo.enums.StatusTask;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "tb_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "description")
    private String description;
    @Column(name = "grade")
    private String grade;
    @Column(name = "status")
    private StatusTask statusTask;
    @Column(name = "priorityTask")
    private PriorityTask priorityTask;

    @ManyToOne
    @JoinColumn(name = "id_User")
    private User user;


    public Task() {
        super();
    }



    public List<Task> ByStatus(String status) {
        return null;
    }

    public List<Task> ByPriority(String priority) {
        return null;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
