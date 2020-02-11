INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Charizard' AND Generation = 1 AND TypeName = 'fire'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Fire Spin'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Charizard' AND Generation = 1 AND TypeName = 'fire'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Wing Attack'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Venusaur' AND Generation = 1 AND TypeName = 'grass'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Vine Whip'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Venusaur' AND Generation = 1 AND TypeName = 'grass'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Razor Leaf'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Alakazam' AND Generation = 1 AND TypeName = 'psychic'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Psycho Cut'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Alakazam' AND Generation = 1 AND TypeName = 'psychic'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Confusion'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Electivire' AND Generation = 4 AND TypeName = 'electric'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Thunder Shock'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Electivire' AND Generation = 4 AND TypeName = 'electric'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Low Kick'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Garbodor' AND Generation = 5 AND TypeName = 'poison'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Acid'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Garbodor' AND Generation = 5 AND TypeName = 'poison'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Infestation'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Dewgong' AND Generation = 1 AND TypeName = 'water'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Frost Breath'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Dewgong' AND Generation = 1 AND TypeName = 'water'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Iron Tail'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Umbreon' AND Generation = 2 AND TypeName = 'dark'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Snarl'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Umbreon' AND Generation = 2 AND TypeName = 'dark'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Feint Attack'));

INSERT INTO PokemonFastMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Vaporeon' AND Generation = 1 AND TypeName = 'water'), (SELECT FastMoveID FROM FastMove WHERE MoveName = 'Water Gun'));