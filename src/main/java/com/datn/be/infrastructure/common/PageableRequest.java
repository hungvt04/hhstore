package com.datn.be.infrastructure.common;

import com.datn.be.infrastructure.constant.PageableDefault;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageableRequest {

    private int size = PageableDefault.SIZE_DEFAULT;

    private int page = PageableDefault.PAGE_DEFAULT;

}
