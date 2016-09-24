#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying LOWP vec4 v_color;
varying vec2 v_texCoords;
uniform sampler2D u_texture;

vec4 getColor(vec2 duv)
{
    return texture2D(u_texture, v_texCoords + duv);
}

 vec4 getAvgColor(float r)
 {
        return (getColor(vec2(0, r)) + getColor(vec2(0, -r))+
        getColor(vec2(r, 0)) + getColor(vec2(-r, 0)))*0.25;
 }

float getLum(vec3 color)
{
    return 0.2126*color.r + 0.7152*color.g + 0.0722*color.b;
}

void main()
{

    vec4 blur = (v_color * (
                  getAvgColor(0.002)*0.25 + getAvgColor(0.003)*0.25 + getAvgColor(0.001)*0.25 + texture2D(u_texture, v_texCoords)*0.25
                  ));
                  gl_FragColor.a = 1.0;
  gl_FragColor.rgb = vec3(getLum(blur.rgb));
}