package com.larry.present.network.base;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
*    
* 项目名称：present-android      
* 类描述： 简化rxjava使用的一些工具方法
* 创建人：Larry-sea   
* 创建时间：2017/4/18 18:56   
* 修改人：Larry-sea  
* 修改时间：2017/4/18 18:56   
* 修改备注：   
* @version    
*    
*/
public class RxjavaUtil {


    /**
     * 绑定事件的静态方法，省去重复代码
     *
     * @param observable 生产者
     * @param observer   消费者ed
     * @param <T>
     */
    public static <T> Subscription subscribe(final Observable<T> observable, final Observer<T> observer) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
