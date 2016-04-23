package com.hy.lyx.fb.gw.wyx.lks.flyingchess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class RoomAct extends AppCompatActivity {
    Button start,back,r,g,b,y,jr,jg,jb,jy;
    Timer closeTimer;
    int me;
    int[] pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);//Activity切换动画
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //init
        closeTimer=new Timer();
        start=(Button)findViewById(R.id.start);
        back=(Button)findViewById(R.id.back);
        r=(Button)findViewById(R.id.R);
        g=(Button)findViewById(R.id.G);
        b=(Button)findViewById(R.id.B);
        y=(Button)findViewById(R.id.Y);
        jr=(Button)findViewById(R.id.jr);
        jg=(Button)findViewById(R.id.jg);
        jb=(Button)findViewById(R.id.jb);
        jy=(Button)findViewById(R.id.jy);
        pos=new int[4];
        //trigger
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(me==-1)
                    Toast.makeText(getApplicationContext(),"you have to choose a color",Toast.LENGTH_SHORT).show();
                else{
                    Game.getDataManager().setPosition(pos);
                    Game.getDataManager().setMyColor(me);
                    Intent intent=new Intent(getApplicationContext(),ChessBoardAct.class);
                    startActivity(intent);
                    closeTimer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    },2000);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Game.getDataManager().setMyColor(-1);
                Intent intent=new Intent(getApplicationContext(),GameInfoAct.class);
                startActivity(intent);
                closeTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        finish();
                    }
                },2000);
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_RED]!=-1)
                    return;
                switch (me){
                    case ChessBoard.COLOR_BLUE:
                        b.setText("");
                        pos[ChessBoard.COLOR_BLUE]=-1;
                        break;
                    case ChessBoard.COLOR_GREEN:
                        g.setText("");
                        pos[ChessBoard.COLOR_GREEN]=-1;
                        break;
                    case ChessBoard.COLOR_YELLOW:
                        y.setText("");
                        pos[ChessBoard.COLOR_YELLOW]=-1;
                        break;
                }
                me=ChessBoard.COLOR_RED;
                pos[ChessBoard.COLOR_RED]=1;
                r.setText("Me");
            }
        });

        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_GREEN]!=-1)
                    return;
                switch (me){
                    case ChessBoard.COLOR_BLUE:
                        b.setText("");
                        pos[ChessBoard.COLOR_BLUE]=-1;
                        break;
                    case ChessBoard.COLOR_RED:
                        r.setText("");
                        pos[ChessBoard.COLOR_RED]=-1;
                        break;
                    case ChessBoard.COLOR_YELLOW:
                        y.setText("");
                        pos[ChessBoard.COLOR_YELLOW]=-1;
                        break;
                }
                me=ChessBoard.COLOR_GREEN;
                pos[ChessBoard.COLOR_GREEN]=1;
                g.setText("Me");
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_BLUE]!=-1)
                    return;
                switch (me){
                    case ChessBoard.COLOR_GREEN:
                        g.setText("");
                        pos[ChessBoard.COLOR_GREEN]=-1;
                        break;
                    case ChessBoard.COLOR_RED:
                        r.setText("");
                        pos[ChessBoard.COLOR_RED]=-1;
                        break;
                    case ChessBoard.COLOR_YELLOW:
                        y.setText("");
                        pos[ChessBoard.COLOR_YELLOW]=-1;
                        break;
                }
                me=ChessBoard.COLOR_BLUE;
                pos[ChessBoard.COLOR_BLUE]=1;
                b.setText("Me");
            }
        });

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_YELLOW]!=-1)
                    return;
                switch (me){
                    case ChessBoard.COLOR_BLUE:
                        b.setText("");
                        pos[ChessBoard.COLOR_BLUE]=-1;
                        break;
                    case ChessBoard.COLOR_GREEN:
                        g.setText("");
                        pos[ChessBoard.COLOR_GREEN]=-1;
                        break;
                    case ChessBoard.COLOR_RED:
                        r.setText("");
                        pos[ChessBoard.COLOR_RED]=-1;
                        break;
                }
                me=ChessBoard.COLOR_YELLOW;
                pos[ChessBoard.COLOR_YELLOW]=1;
                y.setText("Me");
            }
        });

        jr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_RED]==-1)
                {
                    r.setText("robot");
                    pos[ChessBoard.COLOR_RED]=0;
                    jr.setText("-");
                }
                else if(pos[ChessBoard.COLOR_RED]==0)
                {
                    jr.setText("+");
                    r.setText("");
                    pos[ChessBoard.COLOR_RED]=-1;
                }
            }
        });

        jg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_GREEN]==-1)
                {
                    g.setText("robot");
                    pos[ChessBoard.COLOR_GREEN]=0;
                    jg.setText("-");
                }
                else if(pos[ChessBoard.COLOR_GREEN]==0)
                {
                    g.setText("");
                    pos[ChessBoard.COLOR_GREEN]=-1;
                    jg.setText("+");
                }
            }
        });

        jb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_BLUE]==-1)
                {
                    b.setText("robot");
                    pos[ChessBoard.COLOR_BLUE]=0;
                    jb.setText("-");
                }
                else if(pos[ChessBoard.COLOR_BLUE]==0)
                {
                    b.setText("");
                    pos[ChessBoard.COLOR_BLUE]=-1;
                    jb.setText("+");
                }
            }
        });

        jy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pos[ChessBoard.COLOR_YELLOW]==-1)
                {
                    y.setText("robot");
                    pos[ChessBoard.COLOR_YELLOW]=0;
                    jy.setText("-");
                }
                else if(pos[ChessBoard.COLOR_YELLOW]==0)
                {
                    y.setText("");
                    pos[ChessBoard.COLOR_YELLOW]=-1;
                    jy.setText("+");
                }
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        me = -1;
        pos[0] = -1;
        pos[1] = -1;
        pos[2] = -1;
        pos[3] = -1;
        r.setText("");
        g.setText("");
        b.setText("");
        y.setText("");
        jr.setText("+");
        jg.setText("+");
        jb.setText("+");
        jy.setText("+");
    }
}
