package Listener;

import TCP.TCPSocket;

public interface TCPSocketListener {
       public void input(TCPSocket tcp,String msg);
}
