package com.lcb.http.parser;

import com.lcb.http.IRequest;
import com.lcb.http.response.IResponse;
import com.lcb.http.result.IResult;

/**
 * Created by ${lichangbin} on 2019/11/6.
 */

public interface IParser {

    IResult parseResponse(IRequest request, IResponse response);
}
