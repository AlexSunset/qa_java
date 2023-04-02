import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(Parameterized.class)
public class LionTest {

    String sex;
    boolean hasMane;
    public LionTest(String sex, boolean hasMane){
        this.sex = sex;
        this.hasMane = hasMane;
    }

    @Parameterized.Parameters
    public static Object[][] lionSexParameters() {
        return new Object[][] {
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
    }
    Feline feline = Mockito.mock(Feline.class);

    @Test
    public void getKittensCallsFeline() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    public void doesHaveManeReturnActualHasMane() throws Exception {
        Lion lion = new Lion(sex, feline);
        Assert.assertEquals(hasMane, lion.doesHaveMane());
    }

    @Test
    public void eatMeatCallsGetFood() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    public void correctExceptionTextOnLionObjectCreate(){
        try{
            Lion lion = new Lion("Муфаса", feline);
        } catch (Exception e){
            Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка",
                    e.getMessage());
        }
    }
}
