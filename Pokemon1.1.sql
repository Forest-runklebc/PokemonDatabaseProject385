SQLite format 3   @     /                                                               / -�   �    
�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               $ ����nC$                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              PidgeybugforestRoute 1)!+ButterfreebugforestVeridian Forest&+MetapodbugforestVeridian Forest'+Caterpie
bugforestVeridian Forest  Venusaurgrass  Ivysaurgrass	  Bulbasaurgrass
   � �������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
		   � ���h3
���^����                                                                                                                                                                                                                                                                                                                                                                     dragondragon*Aiceflying,ground,grass,dragonwater,ice'psychicfight,poison,psychic4%Eelectricflying,waterground,grass,electric,dragon=/Sgrassground,rock,waterflying,poison,bug,fire,grass,dragon+
-1waterground,rock,firewater,grass,dragon+	'9firebug,grass,icerock,fire,water,dragon)ghostghostnormal,psychic25;bugpoison,grass,psychicfight,flying,ghost,fire'3%rockflying,bug,fire,icefight,ground3?-groundpoison,rock,fire,electricflying,bug,grass+=poisonbug,grasspoison,ground,rock,ghost&+'flyingfight,bug,grassrock,electric1+?fightnormal,rock,iceflying,poison,bug,psychic normalrock
   g �g�������r��z��                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
dragonicepsychicelectric	grass	water
fire		ghostbugrock
ground
poison
flying	fight		normal    s  s��                                                                                                   �a�tablepokemonpokemonCREATE TABLE pokemon(
  name			varchar(25) not null, 
  number		number(3),
  type			varchar(25),
  environment 		varchar(25),
  location	 	varchar(25),
  primary key (number),
  unique(number),
  foreign key (type) references effectiveness(base_type),
  foreign key (number) references trainer_team(p1_id),
  foreign key (number) references trainer_team(p2_id),
  foreign key (number) references trainer_team(p3_id),
  foreign key (number) references trainer_team(p4_id),
  foreign key (number) references trainer_team(p5_id),
  foreign key (number) references trainer_team(p6_id)
)-A indexsqlite_autoindex_pokemon_1pokemon�<''�7tableeffectivenesseffectivenessCREATE TABLE effectiveness(
  base_type	varchar(25),
  strength	varchar(25),
  weakness	varchar(25),
  unique(base_type),
  primary key (base_type)
)9M' indexsqlite_autoindex_effectiveness_1effectiveness   � ���oN(�                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    $'Viridian CitymaleGiovanniBA.$+Cinnabar IslandmaleBlaineI��$%Saffron CityfemaleSabrinaC�!�%Fuchsia CitymaleKogaI���"%Celadon CityfemaleErika:��&)Vermilion CitymaleLt. SurgeI��R#'Cerulean CityfemaleMisty:�h�	#Pewter CitymaleBrockI��
   � ��������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 BA.I��C�!�I���:��I��R:�h�	I��
   � ��������                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          		    #  #2a�����           ��otabletrainertrainerCREATE TABLE trainer(
	id			number(1),
	location		varchar(25),
	sex			varchar(25),
	name			varchar(25),
	phone_number	number(10),
	unique (phone_number),
	unique(id),
	primary key (id),
	foreign key (id) references trainer_team(trainer_id)
)-A indexsqlite_autoindex_trainer_1trainer-A indexsqlite_autoindex_trainer_2trainer	�%%�Ktabletrainer_teamtrainer_teamCREATE TABLE trainer_team(
	trainer_id	varchar(25),
	p1_id		number(3) not null,
	p2_id		number(3),
	p3_id		number(3),
	p4_id		number(3),
	p5_id		number(3),
	p6_id		number(3),
	unique(trainer_id),
	primary key (trainer_id)
)7	K% indexsqlite_autoindex_trainer_team_1trainer_team�_
�%tablegymgymCREATE TABLE gym (
	name		varchar(25),
leader_id	number(1),
badge		varchar(25),
  	type 		varchar(25),
	unique (badge),
	unique(name),
primary key (name),
foreign key (leader_id) references trainer (id)
)%9 indexsqlite_autoindex_gym_1gym%9 indexsqlite_autoindex_gym_2gym   � ����                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        		4	  3
	 2
					1
   � ����                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         432	1   � ��U-��                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     (/#Viridian City GymEarth BadgeGround*3'Cinnabar Island GymVolcano BadgeFire(-#Saffron City GymMarsh BadgePsychic&-!Fuchsia City GymSoul BadgePoison(-'Celadon City GymRainbow BadgeGrass-1'Vermilion City GymThunder BadgeElectric)/'Cerulean City GymCascade BadgeWater%+	'Pewter City GymBoulder BadgeRock
   x ��x�����                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                #Earth Badge'Volcano Badge#Marsh Badge!Soul Badge'Rainbow Badge'Thunder Badge'Cascade Badge'	Boulder Badge
   S ��i����S                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           /Viridian City Gym3Cinnabar Island Gym-Saffron City Gym-Fuchsia City Gym-Celadon City Gym1Vermilion City Gym/Cerulean City Gym+	Pewter City Gym