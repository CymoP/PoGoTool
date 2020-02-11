INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Charizard' AND Generation = 1 AND TypeName = 'fire'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Blast Burn'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Charizard' AND Generation = 1 AND TypeName = 'fire'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Overheat'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Venusaur' AND Generation = 1 AND TypeName = 'grass'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Frenzy Plant'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Venusaur' AND Generation = 1 AND TypeName = 'grass'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Sludge Bomb'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Alakazam' AND Generation = 1 AND TypeName = 'psychic'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Psychic'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Alakazam' AND Generation = 1 AND TypeName = 'psychic'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Shadow Ball'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Electivire' AND Generation = 4 AND TypeName = 'electric'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Wild Charge'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Electivire' AND Generation = 4 AND TypeName = 'electric'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Thunder Punch'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Garbodor' AND Generation = 5 AND TypeName = 'poison'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Gunk Shot'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Garbodor' AND Generation = 5 AND TypeName = 'poison'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Body Slam'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Dewgong' AND Generation = 1 AND TypeName = 'water'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Blizzard'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Dewgong' AND Generation = 1 AND TypeName = 'water'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Aurora Beam'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Umbreon' AND Generation = 2 AND TypeName = 'dark'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Foul Play'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Umbreon' AND Generation = 2 AND TypeName = 'dark'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Last Resort'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Vaporeon' AND Generation = 1 AND TypeName = 'water'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Aqua Tail'));

INSERT INTO PokemonChargedMove (PokemonID, MoveID)
VALUES ((SELECT PokemonID FROM Pokemon WHERE PokemonName = 'Vaporeon' AND Generation = 1 AND TypeName = 'water'), (SELECT ChargedMoveID FROM ChargedMove WHERE MoveName = 'Last Resort'));