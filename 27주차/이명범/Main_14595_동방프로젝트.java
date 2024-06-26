import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] room;

    public static class Room implements Comparable<Room> {
        int x;
        int y;

        public Room(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Room o) {
            if(this.x == o.x) {
                return o.y - this.y;
            }
            return this.x - o.x;
        }
    }



    public static void main(String[] args) throws Exception {
        N = read();
        M = read();

        room = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            room[i] = i;
        }

        Room[] rooms = new Room[M];
        int max = 0;
        for(int i = 0; i < M; i++) {
            int roomX = read();
            int roomY = read();
            rooms[i] = new Room(roomX, roomY);
        }

        Arrays.sort(rooms);

        for(int i = 0; i < M; i++) {
            int roomX = rooms[i].x;
            int roomY = rooms[i].y;
            roomX = Math.max(roomX, max);
            for (int j = roomX; j < roomY; j++) {
                union(j, find(j + 1));
                j = find(j + 1) - 1;
            }
            max = roomY;
        }


        for(int i = 1; i <= N; i++) {
            find(i);
        }

        int cnt = 0;
        int num = room[0];
        for(int i = 0; i <= N; i++) {
            if(room[i] != num) {
                cnt++;
                num = room[i];
            }
        }
        System.out.println(cnt);
    }

    public static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb) return;
        room[pa] = pb;
    }

    public static int find(int a) {
        if(room[a] == a) return a;
        return room[a] = find(room[a]);
    }

    private static int read() throws Exception {
        int c, n = System.in.read() & 15;

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return n;
    }
}