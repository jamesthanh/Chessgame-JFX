
/*
Team Tei x Ren
Le Minh Truyen  - s3627577
Hoang Quang Huy - s3623383
Nguyen Tan Thanh s3634815
OOP - Project - the Chess Game
2017C
 */

/*
Resource:
- All of app icons and piece images can be found on google. They are standard chess images,
- SheetStyle can be found at - http://www.renderx.com/chess.html
- How to build a chess board - https://stackoverflow.com/questions/24082063/chessboard-with-automatic-resizing
- Attempting to try network services - Based on "JavaFX Software: Chat (Server-Client)" - https://www.youtube.com/watch?v=VVUuo9VO2II
- Knight Movements - https://codereview.stackexchange.com/questions/105748/knight-moves-in-chess-game
Chess rule - https://www.chess.com/learn-how-to-play-chess
 */

/*
We try to do an advanced part of the software which is network playing multiple players.
Work are still in the process.
 */


package Networking;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;


public abstract class TrialNetwork {
    private ConnectionThread connThread = new ConnectionThread();

    protected abstract boolean server();

    protected abstract String ipAddress();

    protected abstract int getPort();


    // Called when get an object
    private Consumer<Serializable> getCallBack;

    public TrialNetwork(Consumer<Serializable> dataCallBack) {
        this.getCallBack = dataCallBack;

        // Allow main program to exit even if testNet thread is still running
        connThread.setDaemon(true);
    }

    // Send data
    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
    }


    // Thread is created on startConnection()
    private class ConnectionThread extends Thread {
        private Socket mySocket;
        private ObjectOutputStream out;

        @Override
        public void run() {
            try (
                    // create new server

                    ServerSocket server = server() ? new ServerSocket(getPort()) : null;
                    // If server, wait for incoming connection
                    Socket socket = server() ? server.accept() : new Socket(ipAddress(), getPort());
                    // create output stream
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    // create input stream
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ) {
                getCallBack.accept("Connected");
                this.mySocket = socket;
                this.out = out;

                // Disable message buffering
                socket.setTcpNoDelay(true);

                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    getCallBack.accept(data);
                }
            } catch (ConnectException e) {
                getCallBack.accept("Could not connect to server");
            } catch (Exception e) {
                getCallBack.accept("Connection closed");
            }
        }
    }
}
