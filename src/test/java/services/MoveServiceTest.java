package services;

import model.ChargedMove;
import model.FastMove;
import model.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MoveServiceTest {

    @InjectMocks
    private MoveService moveService;

    @Mock
    private Pokemon pokemon;

    @Mock
    private FastMove fastMove;

    @Mock
    private ChargedMove chargedMove;

    private Fixture fixture;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        fixture = new Fixture();
        fixture.setUp();
    }

    @Test
    public void testGetFastMoveDetailsByName_FastMoveFound() {
        fixture.givenFastMoveIsFound();
        fixture.whenGetFastMoveDetailsByNameIsCalled();
        fixture.thenFastMoveIsReturned();
    }

    @Test
    public void testGetFastMoveDetailsByName_FastMoveNotFound() {
        fixture.givenFastMoveIsNotFound();
        fixture.whenGetFastMoveDetailsByNameIsCalled();
        fixture.thenFastMoveIsNull();
    }

    @Test
    public void testGetChargedMoveDetailsByName_ChargeMoveFound() {
        fixture.givenChargedMoveIsFound();
        fixture.whenGetChargedMoveDetailsByNameIsCalled();
        fixture.thenChargedMoveIsReturned();
    }

    @Test
    public void testGetChargedMoveDetailsByName_ChargeMoveNotFound() {
        fixture.givenChargedMoveIsNotFound();
        fixture.whenGetChargedMoveDetailsByNameIsCalled();
        fixture.thenChargedMoveIsNull();
    }

    private class Fixture {

        private static final String VALID_FAST_MOVE_NAME = "FAST_MOVE_NAME";
        private static final String INVALID_FAST_MOVE_NAME = "INVALID_FAST_MOVE_NAME";
        private static final String VALID_CHARGED_MOVE_NAME = "CHARGED_MOVE_NAME";
        private static final String INVALID_CHARGED_MOVE_NAME = "INVALID_CHARGED_MOVE_NAME";

        private List<FastMove> fastMoveList = new ArrayList<>();
        private List<ChargedMove> chargedMoveList = new ArrayList<>();
        private FastMove actualFastMove;
        private ChargedMove actualChargedMove;

        public void setUp() {
            fastMoveList.add(fastMove);
            chargedMoveList.add(chargedMove);

            when(pokemon.getFastMoveList()).thenReturn(fastMoveList);
            when(pokemon.getChargedMoveList()).thenReturn(chargedMoveList);
        }

        private void givenFastMoveIsFound() {
            when(fastMove.getMoveName()).thenReturn(VALID_FAST_MOVE_NAME);
        }

        private void givenFastMoveIsNotFound() {
            when(fastMove.getMoveName()).thenReturn(INVALID_FAST_MOVE_NAME);
        }

        private void givenChargedMoveIsFound() {
            when(chargedMove.getMoveName()).thenReturn(VALID_CHARGED_MOVE_NAME);
        }

        private void givenChargedMoveIsNotFound() {
            when(chargedMove.getMoveName()).thenReturn(INVALID_CHARGED_MOVE_NAME);
        }

        private void whenGetFastMoveDetailsByNameIsCalled() {
            actualFastMove = moveService.getFastMoveDetailsByName(pokemon, VALID_FAST_MOVE_NAME);
        }

        private void whenGetChargedMoveDetailsByNameIsCalled() {
            actualChargedMove = moveService.getChargedMoveDetailsByName(pokemon, VALID_CHARGED_MOVE_NAME);
        }

        private void thenFastMoveIsReturned() {
            assertEquals(fastMove, actualFastMove);
        }

        private void thenChargedMoveIsReturned() {
            assertEquals(chargedMove, actualChargedMove);
        }

        private void thenFastMoveIsNull() {
            assertNull(actualFastMove);
        }

        private void thenChargedMoveIsNull() {
            assertNull(actualChargedMove);
        }
    }
}