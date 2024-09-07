/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;

/**
 *
 * @author quanghuy
 */
public class ExamUserDetail {
    private UUID id;
    private UUID examDetailId;
    private UUID examUserId;
    private UUID aliasId;

    public ExamUserDetail() {
    }

    public ExamUserDetail(UUID id, UUID examDetailId, UUID examUserId, UUID aliasId) {
        this.id = id;
        this.examDetailId = examDetailId;
        this.examUserId = examUserId;
        this.aliasId = aliasId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getExamDetailId() {
        return examDetailId;
    }

    public void setExamDetailId(UUID examDetailId) {
        this.examDetailId = examDetailId;
    }

    public UUID getExamUserId() {
        return examUserId;
    }

    public void setExamUserId(UUID examUserId) {
        this.examUserId = examUserId;
    }

    public UUID getAliasId() {
        return aliasId;
    }

    public void setAliasId(UUID aliasId) {
        this.aliasId = aliasId;
    }
    
}
