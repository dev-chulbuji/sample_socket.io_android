package com.goqual.a10k.view.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;
import com.goqual.a10k.Application10k;
import com.goqual.a10k.R;
import com.goqual.a10k.util.LogUtil;

/**
 * Created by ladmusician on 2016. 12. 7..
 */

public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private Socket mSocket = null;
    private boolean mIsConnectedSocketServer = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disConnectSocketServer();
    }

    public void connectSocketServer() {
        getSocket().on(Socket.EVENT_CONNECT, onConnect);
        getSocket().on(Socket.EVENT_DISCONNECT, onDisconnect);
        getSocket().on(Socket.EVENT_CONNECT_ERROR, onConnectError);
        getSocket().on(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
        getSocket().connect();
    }

    public void disConnectSocketServer() {
        getSocket().disconnect();
        getSocket().off(Socket.EVENT_CONNECT, onConnect);
        getSocket().off(Socket.EVENT_DISCONNECT, onDisconnect);
        getSocket().off(Socket.EVENT_CONNECT_ERROR, onConnectError);
        getSocket().off(Socket.EVENT_CONNECT_TIMEOUT, onConnectError);
    }

    public Socket getSocket() {
        if (mSocket == null)
            mSocket = ((Application10k)getApplication()).getSocket();
        return mSocket;
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(!mIsConnectedSocketServer) {
                        LogUtil.e(TAG, getString(R.string.SOCKET_SUCCESS_CONNECT));
                        mIsConnectedSocketServer = true;
                    }
                }
            });
        }
    };

    private Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mIsConnectedSocketServer = false;
                    LogUtil.e(TAG, getString(R.string.SOCKET_DISCONNECT));
                }
            });
        }
    };

    private Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mIsConnectedSocketServer = false;
                    LogUtil.e(TAG, getString(R.string.SOCKET_ERROR_CONNECT));
                }
            });
        }
    };
}
