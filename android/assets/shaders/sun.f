#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
varying vec2 v_uv;

uniform sampler2D u_texture;
uniform float u_time;
uniform float u_ratio;

void main()
{
        vec2 p = v_uv - vec2(0.5);
        float l = length(p) * 2.0;
        if(l<=1.0 )
        {
        if(l < 0.5)
        {
        gl_FragColor = v_color * texture2D(u_texture, v_texCoords);
        }
        else
        {

        float a = atan(p.y/p.x) ;
            float d = sin(mod((u_time+l+a)*4.0, 3.1415) )*0.01;

            if(p.x < 0.0)
            {
                a+=3.1415;
            }
            vec2 duv = vec2(d * sin(a), d * cos(a) * u_ratio);
            gl_FragColor = v_color * texture2D(u_texture, v_texCoords + duv);
          //gl_FragColor.a=1.0;
            //gl_FragColor.rgb = a;
            }
        }

}