package com.example.edu.universe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ArrayList<Country> countries = new ArrayList<Country>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fillData();
        Log.d("univ", countries.toString());

        ListView list = (ListView)findViewById(R.id.list);      //list뷰를 불러옴.
        CountryAdapter adt = new CountryAdapter(this, countries, R.layout.list);
        list.setAdapter(adt);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(Country c: countries) {
                    if(c.flag == id) {
                        Intent intent = new Intent(MainActivity.this, CountryActivity.class);
                        intent.putExtra("code", c.code);
                        intent.putExtra("name", c.name);
                        intent.putExtra("flag", c.flag);
                        intent.putExtra("people", c.people);
                        intent.putExtra("area", c.area);
                        intent.putExtra("intro", c.intro);
                        intent.putExtra("capital", c.capital);
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
    }

    private void fillData() {
        countries.add(new Country("be", R.drawable.be, "벨기에", "벨기에는 본래 네덜란드의 남부 지역이었으나 네덜란드 독립 전쟁(1568~1648년) 이후 북부와 분열되면서 다른 길을 걷기 시작했다. 1793년 프랑스는 벨기에를 합병하였다.[1] 나폴레옹 전쟁 이후 벨기에는 북부의 네덜란드 연합 왕국과 합쳐지게 되었으나 절대주의적 통치와 개신교와 로마 가톨릭교회라는 신앙적인 차이로 인해 왈롱인들의 불만이 쌓이기 시작했다.", 342, "브뤼셀", 30528));
        countries.add(new Country("cn", R.drawable.cn, "중국", "중국(중국어 정체: 中國, 중국어 간체: 中国, 영어: China 차이나[*])은 동아시아와 중앙아시아, 그리고 태평양 서부 연안의 도서 지방을 포괄하는 지명이다. 1949년 이후로 2016년 현재까지 중화민국 중화민국과 중화인민공화국 중화인민공화국의 두 나라가 존재하나 결과적으로 중화인민공화국이 승리하고 중화민국은 망명국가이다. 중화민국은 중화인민공화국의 푸젠 성(福建省)에 대해 마주 보고 있다.", 342, "북경", 30528));
        countries.add(new Country("de", R.drawable.de, "독일", "독일 연방 공화국(獨逸聯邦共和國, 독일어: Bundesrepublik Deutschland 분데스레푸블리크 도이칠란트[*]), 줄여서 독일(獨逸, ←일본어: 独逸 ドイツ 도이쓰[*], 독일어: Deutschland 도이칠란트[*] 듣기 (도움말·정보), 문화어: 도이췰란드)은 중앙유럽에 있는 나라이다. 북쪽으로 덴마크와 북해, 발트 해, 동쪽으로 폴란드와 체코, 남쪽으로 오스트리아와 스위스, 서쪽으로 프랑스, 룩셈부르크, 벨기에, 네덜란드와 국경을 맞대고 있다. 독일 영토는 357,021 제곱킬로미터이며, 기후는 주로 온대 기후를 보인다. 인구는 2011년 기준 80,219,695명으로[1] 유럽 연합에서 인구가 가장 많은 나라이며, 이민자 인구는 미국에 이어 세계에서 두 번째로 많다.[2] 공용어는 독일어이고, 소수 민족어나 러시아어, 영어, 소르브어, 덴마크어, 프리지아어도 쓰인다.", 342, "베를린", 30528));
        countries.add(new Country("fr", R.drawable.fr, "프랑스", "프랑스 공화국(-共和國, 프랑스어: République française 레퓌블리크 프랑세즈[*] IPA: [ʀepyblik-fʀɑ̃sɛz]) 또는 프랑스(프랑스어: France, 음차: 불란서(佛蘭西))는 서유럽의 본토와 남아메리카의 프랑스령 기아나를 비롯해 여러 대륙에 걸쳐 있는 해외 레지옹과 해외 영토로 이루어진 국가로서, 유럽 연합 소속 국가 중 가장 영토가 크다. 수도는 파리이다. 프랑스 본토는 남북으로는 지중해에서 영국 해협과 북해까지, 동서로는 라인 강에서 대서양에 이른다. 그 지형적 모양으로 인해 프랑스인들은 종종 이 곳을 L'Hexagone(육각형)이라고 부른다.", 342, "파리", 30528));
        countries.add(new Country("jp", R.drawable.jp, "일본", "일본국(日本國, 일본어: 日本国 にほんこく 니혼코쿠[*][2]、にっぽんこく 닛폰코쿠[*]), 약칭 일본(日本, 일본어: 日本 にほん 니혼[*]、にっぽん 닛폰[*])은 동아시아에 있는 국가이다. 국토는 태평양에 있는 일본 열도의 네 개의 섬으로 이루어진 홋카이도, 혼슈, 시코쿠, 규슈를 중심으로 주변에 산재한 작은 섬으로 구성되어 있다. 총 면적은 37만 7835㎢인데 이는 노르웨이(스발바르 제도와 얀마옌을 포함한 경우)보다 작으며 독일보다 크다. 면적 순으로는 세계 61위이다", 342, "도쿄", 30528));
        countries.add(new Country("ko", R.drawable.ko, "한국", "한국(韓國, 영어: Korea, 문화어: 조선 朝鮮)은 한민족의 나라로서 남북을 구분짓지 않고 전체를 일컫거나, 또는 한반도의 남쪽에 있는 대한민국만을 줄여 이르는 말이다. 한국의 강역은 현재 한반도와 그 부속 도서를 말한다..", 342, "서울", 30528));
        countries.add(new Country("tr", R.drawable.tr, "터키", "터키 공화국(터키어: Türkiye Cumhuriyeti 튀르키예 줌후리예티[*]) 또는 단순히 터키(터키어: Türkiye 튀르키예[*], 문화어: 뛰르끼예, 떠끼)는 서남아시아의 아나톨리아와 유럽 남동부 발칸 반도의 동부 트라키아에 걸친 나라이다. 수도는 앙카라이며, 공용어는 터키어, 국교를 명시하지는 않고 있으나[1] 국민의 절대 다수가 이슬람교를 신봉한다.", 342, "터키", 30528));
        countries.add(new Country("uk", R.drawable.uk, "영국", "영국(英國, 잉글랜드에서 유래)은 유럽 북서부 해안의 브리튼 제도에 위치한 주권국이자 섬나라로, 북해, 영국 해협, 아일랜드 해 및 대서양에 접하여 있으며 그레이트브리튼 섬의 잉글랜드, 스코틀랜드, 웨일스 및 아일랜드 섬 북부의 북아일랜드로 네 개의 홈 네이션스로 이루어져 있는 연합국가이다. 수도는 런던이고 연합국의 구성을 이루는 각 나라들은 자치권을 보장받고 있다. 스코틀랜드, 웨일스, 북아일랜드의 수도는 각각 에든버러, 카디프, 벨파스트이다.", 342, "런던", 30528));
        countries.add(new Country("us", R.drawable.us, "미국", "미국(표준어: 美國, 문화어: 米國), 정식 명칭으로 미합중국(美合衆國, 영어: United States of America 유나이티드 스테이츠 어브 어메리커[*])[1] 은 주 50개와 특별구 1개로 이루어진 연방제 공화국이다. 태평양의 하와이 주를 제외한 모든 주와 수도인 워싱턴 D.C.는 북아메리카에 있으며, 북측으로는 캐나다와 남측으로는 멕시코와 국경을 맞댄다. 북아메리카 북서측에 있는 알래스카 주는 동측으로는 캐나다와 서측으로는 베링 해협을 사이로 러시아와 마주한다. 미합중국은 태평양과 카리브 해에 해외 영토를 보유한다.", 342, "워싱턴", 30528));
        countries.add(new Country("za", R.drawable.za, "남아프리카공화국", "남아프리카공화국(南Africa共和國, 영어: Republic of South Africa 리퍼블릭 오브 사우스아프리카[*], RSA, 아프리칸스어: Republiek van Suid-Afrika 레퓌블릭 판 사위트아프리카)은 아프리카 대륙의 최남단에 자리한 공화국이다. 줄여서 남아공(南阿共)이라고 불리기도 한다.", 342, "케이프타운", 30528));

    }
}
