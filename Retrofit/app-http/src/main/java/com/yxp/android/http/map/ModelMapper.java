package com.yxp.android.http.map;

import java.util.List;

/**
 * site :  http://www.andcup.com
 * email:  amos@andcup.com
 * github: https://github.com/andcup
 * Created by Amos on 2016/4/29.
 */
public interface ModelMapper<T1, T2> {
    T1 map(List<T2> list, int number);
}
