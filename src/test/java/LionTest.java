import com.example.Feline;
import com.example.Lion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


public class LionTest {

    boolean hasMane;
    Feline feline = Mockito.mock(Feline.class);

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getKittensCallsFeline() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @Test
    public void eatMeatCallsGetFood() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    public void correctExceptionOnLionObjectCreate() {
        Exception exception = Assert.assertThrows(Exception.class, () -> new Lion(" ", feline));
        Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }
}
