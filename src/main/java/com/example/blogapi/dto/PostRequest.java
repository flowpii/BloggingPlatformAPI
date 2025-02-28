package com.example.blogapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;

@Data
public class PostRequest {
    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题长度不能超过100字符")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @Size(max = 50, message = "分类长度不能超过50字符")
    private String category;

    private List<String> tags;
}
