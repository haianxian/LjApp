package com.lj.app.tools.utils;


import com.wilddog.client.DataSnapshot;
import com.wilddog.client.OnDisconnect;
import com.wilddog.client.SyncError;
import com.wilddog.client.SyncReference;
import com.wilddog.client.ValueEventListener;
import com.wilddog.client.WilddogSync;
import com.wilddog.wilddogauth.WilddogAuth;
import com.wilddog.wilddogauth.core.Task;
import com.wilddog.wilddogauth.core.listener.OnCompleteListener;
import com.wilddog.wilddogauth.core.result.AuthResult;

import java.util.HashMap;

/**野狗（实时通讯）
 * Created by 13717 on 2017/12/12.
 */

public class WilddogSyncUtils {

    public static final String TAG = "WilddogSync";
    public static int FROM_TAG = 0; // 从首页随机对接过来，还是从首页列表过来
    private String matchToken; // 首页随机对接的matchtoken
    private String playListId;
    private static WilddogSyncUtils wilddogSyncUtils;
    private String clientId = "7e14dae6d1db45ae89d73338348e0326";
    private String userId = "446";
    private String nickName = "测试账号";
    private String avatar = "";
    private WilddogAuth auth;
    private SyncReference ref;
    private WilddogSyncUtils(){

    }

    public static WilddogSyncUtils getInstance(){
        if(wilddogSyncUtils == null){
            wilddogSyncUtils = new WilddogSyncUtils();
        }
        return wilddogSyncUtils;
    }

    // 实时通讯实例
    public SyncReference getWildDogRef(){
        ref = WilddogSync.getInstance().getReference();
//        OnDisconnect onDisconnectRef = ref.onDisconnect();
//        onDisconnectRef.setValue("I disconnected");
//        LogUtil.i(TAG, "I disconnected");
        return ref;
    }

    // 身份认证
    public WilddogAuth getWilddogAuth(){
        auth=WilddogAuth.getInstance();
   //使用 signInWithCustomToken() 方法进行认证
        auth.signInWithCustomToken(clientId).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {
                if(task.isSuccessful()){
                    LogUtil.i(TAG,"认证成功");
                } else {
                    LogUtil.i(TAG,"认证失败"+task.getException().toString());
                }
            }
        });
        return auth;
    }

    // 退出登录
    public void exitWilddog(){
        if(auth != null){
            auth.signOut();
        }
    }

    // 放映厅在线用户
    public void playHallOnLine(int fromTag,String playListId, String matchToken){

        SyncReference srf =  getWildDogRef();
        FROM_TAG = fromTag;
        this.matchToken = matchToken;
        this.playListId = playListId;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("nickname", nickName);
        hashMap.put("avatar", avatar);
        if(srf == null) return;
        srf.child("playlist/"+playListId+"/"+clientId).setValue(hashMap, new SyncReference.CompletionListener() {
            @Override
            public void onComplete(SyncError syncError, SyncReference syncReference) {
                LogUtil.i("onDataChange>>playHallOnLine");
                if(syncError != null){
                    int errorCode = syncError.getErrCode();
                    LogUtil.i("onDataChange>>errorCode"+errorCode);
                    String detail = syncError.getDetails();
                    String message = syncError.getMessage();
                }

            }
        });
    }

    // 主动取消匹配（删除掉User里面的数据）
    public void cancleMatch(){
        if(ref == null) return;
        ref.child("user/"+clientId).removeValue();
        LogUtil.i(TAG, "cancleMatch>>"+ref.child("user/"+clientId).toString());
    }

    // (网络连接中断时/用户主动断开)记录用户已离线
    public void userDisconnect(){
        if(ref == null) return;
        OnDisconnect onDisconnectRef = ref.onDisconnect();
        onDisconnectRef.setValue("I disconnected");
    }

    // 手动断开连接(一个应用可以创建多个 Wilddog Sync 实例，但多个实例只会复用同一个长连接。 并且goOffline()方法 和 goOnline()方法会控制全局的在线和离线)
    public void syncRefOffLine(){
      if(ref == null) return;
        ref.child("playlist/"+playListId).removeValue();
        SyncReference postReference = ref.child("playlist/"+playListId);
        postReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LogUtil.i("onDataChange>>syncRefOffLine"+dataSnapshot.toString());
            }

            @Override
            public void onCancelled(SyncError syncError) {

            }
        });
        ref.child("matching/"+playListId+"/"+clientId).removeValue();
        ref.goOffline();
        LogUtil.i("onDataChange>>syncRefOffLine");
    }

}
