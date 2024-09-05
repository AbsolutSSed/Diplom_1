import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsWithStab() {
        Mockito.lenient().when(bunMock.getName()).thenReturn("Булка");
        Mockito.lenient().when(bunMock.getPrice()).thenReturn(1223f);
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientWithStab() {
        Mockito.lenient().when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.lenient().when(ingredientMock.getName()).thenReturn("Большая котлета - сатурн");
        Mockito.lenient().when(ingredientMock.getPrice()).thenReturn(0.71f);
        burger.addIngredient(ingredientMock);
        assertEquals(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientWithStab() {
        Mockito.lenient().when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.lenient().when(ingredientMock.getName()).thenReturn("Чили-соус");
        Mockito.lenient().when(ingredientMock.getPrice()).thenReturn(476f);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientWithStab() {
        Ingredient secondIngredientMock = mock(Ingredient.class);
        Mockito.lenient().when(ingredientMock.getPrice()).thenReturn(3.14f);
        Mockito.when(ingredientMock.getName()).thenReturn("test");
        Mockito.lenient().when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.lenient().when(secondIngredientMock.getPrice()).thenReturn(0.14f);
        Mockito.lenient().when(secondIngredientMock.getType()).thenReturn(IngredientType.FILLING);
        Mockito.lenient().when(secondIngredientMock.getName()).thenReturn("Rocket#16");
        burger.addIngredient(ingredientMock);
        burger.addIngredient(secondIngredientMock);
        burger.moveIngredient(0, 1);
        assertEquals("test", burger.ingredients.get(1).getName());

    }
    @Test
    public void getPrice() {
        Mockito.lenient().when(bunMock.getName()).thenReturn("Capybara");
        Mockito.lenient().when(ingredientMock.getName()).thenReturn("Capybara sauce");
        Mockito.when(bunMock.getPrice()).thenReturn(300f);
        Mockito.when(ingredientMock.getPrice()).thenReturn(100f);
        Mockito.lenient().when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        float actual = burger.getPrice();
        float expectedPriceDoubleBun = bunMock.getPrice() * 2 + ingredientMock.getPrice();
        assertEquals(expectedPriceDoubleBun, actual, 0);
    }
    @Test
    public void getReceipt() {
        Mockito.when(bunMock.getName()).thenReturn("PEPEGA");
        Mockito.when(bunMock.getPrice()).thenReturn(300f);
        Mockito.when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientMock.getName()).thenReturn("соус BBQ");
        Mockito.when(ingredientMock.getPrice()).thenReturn(100f);
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        String actual = burger.getReceipt();
        String expected = String.format("(==== PEPEGA ====)%n" +
                "= sauce соус BBQ =%n" +
                "(==== PEPEGA ====)%n" +
                "%n" +
                "Price: 700,000000%n");
        assertEquals(expected, actual);
    }
}
