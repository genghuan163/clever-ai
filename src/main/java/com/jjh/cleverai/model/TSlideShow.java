package com.jjh.cleverai.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TSlideShow extends BaseEntity implements Serializable {


    private String imageUrl;

    private String linkUrl;

    private String name;

    private Integer sort;
}