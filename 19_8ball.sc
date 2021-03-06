// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************
// **************************************8Ball Mission**************************************
// **************************************Luigi's Girls**************************************
// *****************************************************************************************
// *****************************************************************************************
// *****************************************************************************************

:M19_GIVEMELIBERTY
03A4: name_thread 'EIGHT' 

// Mission start stuff

gosub @MISSION_START_EIGHTBALL 
if 
	wasted_or_busted 
then
	gosub @MISSION_FAILED_EIGHTBALL 
end

:MISSION_EIGHTBALL_END
gosub @MISSION_CLEANUP_EIGHTBALL 
end_thread 

// ***************************************Mission Start*************************************

:MISSION_START_EIGHTBALL
0004: $ONMISSION = 1 
0004: $ON_MISSION_FOR_8BALL = 1 
0317: increment_mission_attempts 
01B6: set_weather WEATHER_CLOUDY
00C0: set_current_time 4 0 
03CB: load_scene 807.0 -937.0 36.5625  // R*: "THIS MIGHT HAVE TO COME OUT!!!!!!!!!!!!!!!!!!!!!!!!!!!"
0001: wait 0 ms 
0004: $FLAG_BLIP_ON_EIGHTBALL = 0 
0004: $RADAR_BLIP_PED1_EIGHTBALL = 0 
0004: $FLAG_PLAYER_GOT_CAR_MESSAGE1_EIGHTBALL = 0 
0004: $FLAG_PLAYER_IN_AREA = 0 
0004: $FLAG_EIGHTBALL_IN_AREA = 0 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 0

// luigi variables
0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_LM1 = 0 
0004: $FLAG_PLAYER_NOT_IN_CAR_MESSAGE_LM1 = 0 
0004: $NO_OF_PASSENGERS_CAR_LM1 = 0 
0004: $FLAG_GIRL1_IN_CAR_LM1 = 0 
0004: $PASSENGER_COUNT_LM1 = 0 
0004: $TOTAL_SPACE_IN_CAR_LM1 = 0 
0004: $FLAG_BLIP_ON_GIRL1_LM1 = 0 
0004: $FLAG_COORD_BLIP_ON = 0 
0004: $BLOB_FLAG = 1 

// luigi blip stuff
0004: $FLAG_LUIGI_COORD1_BLIP_CREATED = 0 
0004: $FLAG_LUIGI_PED1_BLIP_CREATED = 0 
0004: $FLAG_GIRL1_IN_GROUP_LM1 = 0 
0004: $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 = 0 
0004: $FLAG_PLAYER_IN_AREA_LM1 = 0 
0004: $FLAG_EIGHTBALL_IN_AREA_LM1 = 0 
0004: $FLAG_HELP_8BALL1 = 0 
0004: $FLAG_HELP_8BALL2 = 0 
0004: $FLAG_CAR_MESSAGE_8BALL = 0 
0004: $FLAG_BRAKE_MESSAGE = 0 
0004: $FLAG_HBRAKE_MESSAGE = 0 
0004: $FLAG_LOOK2_8BALL = 0 
0004: $FLAG_LOOK3_8BALL = 0 
0004: $FLAG_MISTY_STOP = 0 
0004: $FLAG_PLAYER_HAD_CAMERA_MESSAGE_8BALL = 0 
0004: $FLAG_GIRL_IN_GROUP_LM1 = 0 
0004: $FLAG_TIMER_STOPPED_FLASHING_8BALL = 0 
if
	0038:   $FLAG_REACHED_HIDEOUT == 0 
then 
	0247: request_model #KURUMA 
	023C: load_special_actor 'EIGHT' as 1 
	038B: load_all_models_now 
	0005: $CAR_8BALL_X = 0.0 
	0005: $CAR_8BALL_Y = 0.0 
	0005: $CAR_8BALL_Z = 0.0 
	0005: $CAR_8BALL_HEADING = 0.0 
	0004: $CAR_COLOUR1_8BALL = 0 
	0004: $CAR_COLOUR2_8BALL = 0 
else
	goto @HIDEOUT_REACHED
end
if and
	0038:   $BROKEN_BRIDGE_OBJECTS_INITIALIZED == 0 
	8038:   not $DEBUGUNLOCKISLANDS == 1
then
	029B: $BROKEN_BRIDGE_REMAINS = init_object #BRIDGEFUKA at 715.6875 -937.875 40.1875 
	01C7: remove_object_from_mission_cleanup_list $BROKEN_BRIDGE_REMAINS 
	029B: $BROKEN_BRIDGE_POLICE_CARS = init_object #BRIDGEFUKB at 787.8125 -939.1875 38.9375 
	01C7: remove_object_from_mission_cleanup_list $BROKEN_BRIDGE_POLICE_CARS 
	0004: $BROKEN_BRIDGE_OBJECTS_INITIALIZED = 1 
end

0171: set_player $PLAYER_CHAR z_angle_to 180.0 
00A5: $CAR_EIGHTBALL = create_car #KURUMA at 812.0 -945.5 35.75
0229: set_car $CAR_EIGHTBALL color_to 58 1 
0175: set_car $CAR_EIGHTBALL z_angle_to 262.375 
009A: $EIGHTBALL = create_char PEDTYPE_SPECIAL model #SPECIAL01 at 811.875 -942.4375 -100.0
0245: set_actor $EIGHTBALL walk_style_to ANIM_GANG2_PED
01ED: clear_actor $EIGHTBALL threat_search 
01BE: set_actor $EIGHTBALL to_look_at_spot 811.875 -939.9375 35.75 
022D: set_actor $EIGHTBALL to_look_at_player $PLAYER_CHAR 
016E: override_next_restart at 811.875 -939.9375 35.75 heading 180.0 //Restarts at the bridge
0177: set_object $PORTLAND_HIDEOUT_DOOR z_angle_to 0.0 
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
02A2: create_particle POBJECT_DARK_SMOKE 0 at 791.625 -936.875 38.3125 //SMOKE ON CARS
02A2: create_particle POBJECT_DARK_SMOKE 0 at 788.3125 -938.4375 38.0625 
02A2: create_particle POBJECT_DARK_SMOKE 0 at 786.4375 -942.375 39.75 
02A2: create_particle 10 0 at 783.5625 -938.5 38.4375 //FIRE ON CARS
02A2: create_particle 10 0 at 790.5 -935.625 38.0 
02A2: create_particle 10 0 at 789.25 -938.875 38.125 
018E: stop_sound $FIRE_SOUND_8BALL 
018D: $FIRE_SOUND_8BALL = create_sound SOUND_PRETEND_FIRE_LOOP at 790.5 -935.625 38.0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
015F: set_camera_position 785.0 -936.75 39.75 0.0 rotation 0.0 0.0 
0159: camera_on_ped $EIGHTBALL mode FIXED switchstyle JUMP_CUT
03CF: load_wav 'LIB_A1' 
0169: set_fade_color 0 0 0 
016A: fade 1 for 1000 ms 
while fading
    wait 0 ms
end 
0169: set_fade_color 0 0 0 
while 83D0:   not wav_loaded 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end
wait 2000 ms
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

015F: set_camera_position 804.5625 -933.0 39.9375 0.0 rotation 0.0 0.0 
0160: point_camera 805.1875 -933.6875 39.5625 switchstyle JUMP_CUT
00BA: print_big 'EBAL' duration 15000 ms style 2  // 'GIVE ME LIBERTY'
03D1: play_wav 
00BC: print_now 'EBAL_A' duration 5000 ms flag 1  // I know a place on the edge of the Red Light District where we can lay low,

while 83D2:   not wav_ended 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

03D5: remove_text 'EBAL_A'  // I know a place on the edge of the Red Light District where we can lay low,
03CF: load_wav 'LIB_A2' 

while 83D0:   not wav_loaded 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

03D1: play_wav 
00BC: print_now 'EBAL_A1' duration 5000 ms flag 1  // but my hands are all messed up so you better drive, brother.
if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_A1'  // but my hands are all messed up so you better drive, brother.
end

gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

022F: set_actor $EIGHTBALL stop_looking 
01D4: actor $EIGHTBALL go_to_car $CAR_EIGHTBALL and_enter_it_as_a_passenger 
while 80DB:   not is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL 
	wait 0 ms
	if
		03D2:   wav_ended 
	then
		03D5: remove_text 'EBAL_A1'  // but my hands are all messed up so you better drive, brother.
	end
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_A1'  // but my hands are all messed up so you better drive, brother.
end

// This will tune the radio to HEAD RADIO
if
    0038:   $FLAG_DONE_RADIO_8BALL == 0  
then
    041E: set_radio_station HEAD_RADIO 0
    0004: $FLAG_DONE_RADIO_8BALL = 1  
else
    041E: set_radio_station HEAD_RADIO -1
end

039E: set_char_cant_be_dragged_out $EIGHTBALL to 1 
02A3: toggle_widescreen 0 
015A: restore_camera 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
0186: $RADAR_BLIP_CAR1_EIGHTBALL = create_marker_above_car $CAR_EIGHTBALL
gosub @SWITCH_CAR_GENERATOR  
wait 500 ms

if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_A1'  // but my hands are all messed up so you better drive, brother.
end
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

// gives the player the help message for entering cars
03E5: text_box 'EBAL_1'  // Press the~h~ ~k~~VEHICLE_ENTER_EXIT~ button~w~ to ~h~enter ~w~or ~h~exit~w~ a vehicle.

0006: 17@ = 0 // Timer for police wanted level stuff
wait 3000 ms
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

// Waiting for the player to get into the car
while true
	if or
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
		80DB:   not is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL 
	jf break
	wait 0 ms
	gosub @CHECK_CLEAR_PLAYER_WANTED_LEVEL
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end 

// creates two cops cars that drive onto the bridge
00A5: $COP_CAR1_8BALL = create_car #POLICE at 1083.188 -945.0 13.75
0129: $COP1_8BALL = create_actor PEDTYPE_CIVMALE #COP in_car $COP_CAR1_8BALL driverseat
01ED: clear_actor $COP1_8BALL threat_search 
0175: set_car $COP_CAR1_8BALL z_angle_to 90.0 
0397: car $COP_CAR1_8BALL siren = 1 
00AE: set_car_driving_style $COP_CAR1_8BALL to DRIVINGMODE_AVOIDCARS
00AD: set_car_cruise_speed $COP_CAR1_8BALL to 20.0 
00A7: car_goto_coordinates $COP_CAR1_8BALL coords 713.875 -916.6875 42.0 
00A5: $COP_CAR2_8BALL = create_car #POLICE at 1074.063 -946.6875 13.75
0129: $COP2_8BALL = create_actor PEDTYPE_CIVMALE #COP in_car $COP_CAR2_8BALL driverseat
01ED: clear_actor $COP2_8BALL threat_search 
0175: set_car $COP_CAR2_8BALL z_angle_to 90.0 
0397: car $COP_CAR2_8BALL siren = 1 
00AE: set_car_driving_style $COP_CAR2_8BALL to DRIVINGMODE_AVOIDCARS
00AD: set_car_cruise_speed $COP_CAR2_8BALL to 20.0 
00A7: car_goto_coordinates $COP_CAR2_8BALL coords 718.6875 -922.1875 42.0 
0164: disable_marker $RADAR_BLIP_CAR1_EIGHTBALL  
wait 500 ms 
gosub @CHECK_CLEAR_PLAYER_WANTED_LEVEL
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

// Acceleration help messages
0293: $CONTROLMODE = current_controls 
if
	$CONTROLMODE == 3
then
	03E5: text_box 'HELP4_D'  // Push the~h~ right analog stick~w~ up to ~h~accelerate.
else
	03E5: text_box 'HELP4_A'  // Press the~h~ ~k~~VEHICLE_ACCELERATE~ button~w~ to ~h~accelerate.
end

018A: $RADAR_BLIP_COORD1_EIGHTBALL = create_checkpoint_at 875.0 -309.0 -100.0
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 1 
0006: 16@ = 0 
0004: $BLOB_FLAG = 1 
gosub @CHECK_CLEAR_PLAYER_WANTED_LEVEL
03CF: load_wav 'LIB_A' 
0006: 16@ = 0 

while 10000 > 16@
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL
	gosub @CHECK_MARKER_HELP1_STATUS_EIGHTBALL
end

03E5: text_box 'EBAL_3'  // This is the ~h~radar~w~. Use it to navigate the city, follow the ~h~blip~w~ on the ~h~radar~w~ to find the hideout!
03E7: flash_hud HUD_FLASH_RADAR
0006: 16@ = 0 

// waiting for the player to get to Luigi's
while true
	if or
		81A0:   not player $PLAYER_CHAR stopped $BLOB_FLAG 879.375 -303.375 7.25 870.0625 -311.6875 10.0 
		81AA:   not actor $EIGHTBALL stopped 0 879.375 -303.375 7.25 870.0625 -311.6875 10.0 
		8442:   not player $PLAYER_CHAR in_car $CAR_EIGHTBALL 
		8448:   not actor $EIGHTBALL in_car $CAR_EIGHTBALL 
		83D0:   not wav_loaded 
	jf break
	wait 0 ms
	gosub @CHECK_CLEAR_PLAYER_WANTED_LEVEL
	if 
		$FLAG_TIMER_STOPPED_FLASHING_8BALL == 0
	then
		if 
			16@ > 4000
		then
			03E7: flash_hud -1 
			0004: $FLAG_TIMER_STOPPED_FLASHING_8BALL = 1
		end
	end
	if
		0038:   $FLAG_BRAKE_MESSAGE == 0 
	then
		if
			0029:   16@ >= 10000 
		then
			0293: $CONTROLMODE = current_controls 
			if
				$CONTROLMODE == 3
			then
				03E5: text_box 'HELP5_D'  // Pull the ~h~right analog stick~w~ back to ~h~brake~w~, or to ~h~reverse~w~ if the vehicle has stopped.
			else
				03E5: text_box 'HELP5_A'  // Press the~h~ ~k~~VEHICLE_BRAKE~ button~w~ to ~h~brake~w~, or to ~h~reverse~w~ if the vehicle has stopped.
			end
			0004: $FLAG_BRAKE_MESSAGE = 1 
			0006: 16@ = 0 
		end
	end
	if
		$FLAG_HANDBRAKE_MESSAGE == 0
	then
		if
			0029:   16@ >= 10000 
		then
			03E5: text_box 'HELP6_A'  // Press the~h~ ~k~~VEHICLE_HANDBRAKE~ button ~w~to apply the vehicle's ~h~handbrake.
			$FLAG_HANDBRAKE_MESSAGE = 1
		end
	end
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL
	gosub @CHECK_MARKER_HELP1_STATUS_EIGHTBALL
end

03E6: remove_text_box 
0164: disable_marker $RADAR_BLIP_COORD1_EIGHTBALL 

// ******************************Player and 8ball are at base scripted cutscene*************

02A3: toggle_widescreen 1 
0110: clear_player $PLAYER_CHAR wanted_level 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
01B4: set_player $PLAYER_CHAR controllable 0
if 
	8119:   not car $COP_CAR1_8BALL wrecked 
then
	00A6: delete_car $COP_CAR1_8BALL 
end

if 
	8119:   not car $COP_CAR2_8BALL wrecked 
then
	00A6: delete_car $COP_CAR2_8BALL 
end
0395: clear_area 1 at 886.75 -310.0625 range -100.0 2.0 
00AA: get_car_coordinates $CAR_EIGHTBALL store_to $CAR_8BALL_X $CAR_8BALL_Y $CAR_8BALL_Z 
0174: $CAR_8BALL_HEADING = car $CAR_EIGHTBALL z_angle 
03F3: get_car $CAR_EIGHTBALL color $CAR_COLOUR1_8BALL $CAR_COLOUR2_8BALL 

while 834D:   not rotate_object $PORTLAND_HIDEOUT_DOOR from_angle 210.0 to 10.0 collision_check 0 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

0395: clear_area 1 at 889.6875 -308.1875 range 8.5625 3.0 // This should remove any stuff that is in the way for the cut-scene
01F5: $PLAYER_ACTOR = create_emulated_actor_from_player $PLAYER_CHAR 
01ED: clear_actor $PLAYER_ACTOR threat_search 
01D3: actor $EIGHTBALL leave_car $CAR_EIGHTBALL

while 00DB:   is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

03D1: play_wav 
00BC: print_now 'EBAL_B' duration 7000 ms flag 1  // This is the place right here, let's get off the street and find a change of clothes!
0239: actor $EIGHTBALL run_to 892.6875 -308.5625 
if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_B'  // This is the place right here, let's get off the street and find a change of clothes!
end
wait 1000 ms
if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_B'  // This is the place right here, let's get off the street and find a change of clothes!
end
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
if
	03D2:   wav_ended 
then
	03D5: remove_text 'EBAL_B'  // This is the place right here, let's get off the street and find a change of clothes!
end

01D3: actor $PLAYER_ACTOR leave_car $CAR_EIGHTBALL 
0395: clear_area 1 at 868.625 -311.6875 range 8.25 1.0 
if 
	0339:   objects_in_cube 870.375 -309.875 6.0 to 865.1875 -314.6875 12.0 flags 0 1 1 1 1 
then
	015F: set_camera_position 848.25 -295.25 19.125 0.0 rotation 0.0 0.0 //high camera that points to the water tower
	0160: point_camera 849.0625 -295.75 19.125 switchstyle JUMP_CUT
else
	015F: set_camera_position 868.625 -311.6875 8.25 0.0 rotation 0.0 0.0 //low new camera that points to the save house
	0160: point_camera 869.5625 -311.5 8.5 switchstyle JUMP_CUT
end
while 00DB:   is_char_in_car $PLAYER_ACTOR car $CAR_EIGHTBALL
	wait 0 ms
	if
		03D2:   wav_ended 
	then
		03D5: remove_text 'EBAL_B'  // This is the place right here, let's get off the street and find a change of clothes!
	end
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end

//Make player walk into the doors and get a change of clothes

0239: actor $PLAYER_ACTOR run_to 892.375 -308.5 
0006: 17@ = 0 

while true
	if or
		8038:   not  $FLAG_PLAYER_IN_AREA == 2 
		8038:   not  $FLAG_EIGHTBALL_IN_AREA == 2 
	jf break
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	if and
		0038:   $FLAG_PLAYER_IN_AREA == 0 
		00ED:   actor $PLAYER_ACTOR #NULL 892.375 -308.5 radius 0.5 0.5 
	then
		0239: actor $PLAYER_ACTOR run_to 892.375 -305.5625 
		0004: $FLAG_PLAYER_IN_AREA = 1 
	end
	if and
		0038:   $FLAG_EIGHTBALL_IN_AREA == 0 
		00ED:   actor $EIGHTBALL #NULL 892.6875 -308.5625 radius 0.5 0.5
	then
		0239: actor $EIGHTBALL run_to 894.1875 -304.25 
		0004: $FLAG_EIGHTBALL_IN_AREA = 1
	end
	if and
		0038:   $FLAG_PLAYER_IN_AREA == 1
		00ED:   actor $PLAYER_ACTOR #NULL 892.375 -305.5625 radius 0.5 0.5 
	then
		0004: $FLAG_PLAYER_IN_AREA = 2
	end
	if and
		0038:   $FLAG_EIGHTBALL_IN_AREA == 1
		00ED:   actor $EIGHTBALL #NULL 894.1875 -304.25 radius 0.5 0.5  
	then
		0004: $FLAG_EIGHTBALL_IN_AREA = 2 
	end
	if 
		0029:   17@ >= 10000 
	then
		if or
			8038:   not  $FLAG_PLAYER_IN_AREA == 2 
			8038:   not  $FLAG_EIGHTBALL_IN_AREA == 2 
		then
			0055: set_player_coordinates $PLAYER_CHAR to 892.375 -305.5625 7.6875 
			009F: char_set_idle $PLAYER_ACTOR 
			00A1: set_char_coordinates $EIGHTBALL to 894.1875 -304.25 7.6875 
			009F: char_set_idle $EIGHTBALL
			goto @MISSION_BLOKE_STUCK_8BALL
		end
	end
end //while

:MISSION_BLOKE_STUCK_8BALL
015F: set_camera_position 886.75 -310.0625 9.875 0.0 rotation 0.0 0.0 
0160: point_camera 887.6875 -309.75 9.75 switchstyle JUMP_CUT
009F: char_set_idle $EIGHTBALL 
011C: actor $PLAYER_ACTOR clear_objective 
03E5: text_box 'S_PROMP'  // When not on a mission you can ~h~save your game here~w~, this will advance time six hours.
wait 4000 ms
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

03E5: text_box 'S_PROM2'  // The garage next door can store one vehicle when you save your game.
0160: point_camera 887.75 -310.5 9.6875 switchstyle INTERPOLATION
0055: set_player_coordinates $PLAYER_CHAR to 895.875 -311.375 7.6875 
00A1: set_char_coordinates $EIGHTBALL to 884.25 -309.1875 7.5625 

// Clothes change

//8-Ball change
if 
	8118:   not actor $EIGHTBALL dead 
then
	0352: set_actor $EIGHTBALL skin_to 'EIGHT2' 
	while 8248:   not model #SPECIAL01 available 
		wait 0 ms
		gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	end //while
	if 
		8118:   not actor $EIGHTBALL dead 
	then
		0353: refresh_actor $EIGHTBALL 
	end
end

//Player change
if 
	8118:   not actor $PLAYER_ACTOR dead 
then
	0352: set_actor $PLAYER_ACTOR skin_to 'PLAYER' 
	while 8248:   not model #NULL available 
		wait 0 ms
		gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	end //while
	if 
		8118:   not actor $PLAYER_ACTOR dead 
	then
		0353: refresh_actor $PLAYER_ACTOR 
	end
end

wait 3000 ms
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

0055: set_player_coordinates $PLAYER_CHAR to 883.5 -308.1875 7.5625 
01B7: release_weather 
01D4: actor $EIGHTBALL go_to_car $CAR_EIGHTBALL and_enter_it_as_a_passenger 
wait 1500 ms
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL

01D5: actor $PLAYER_ACTOR go_to_and_drive_car $CAR_EIGHTBALL 
0395: clear_area 1 at 868.625 -311.6875 range 8.25 1.0  
if 
	0339:   objects_in_cube 870.375 -309.875 6.0 to 865.1875 -314.6875 12.0 flags 0 1 1 1 1 
then
	015F: set_camera_position 848.25 -295.25 19.125 0.0 rotation 0.0 0.0 //high camera that points to the water tower
	0160: point_camera 849.0625 -295.75 19.125 switchstyle JUMP_CUT 
else
	015F: set_camera_position 868.625 -311.6875 8.25 0.0 rotation 0.0 0.0 //low new camera that points to the save house
	0160: point_camera 869.5625 -311.5 8.5 switchstyle JUMP_CUT
end
03E6: remove_text_box 

while true
	if or
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
		80DB:   not is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL
	jf break
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end //while
while 834D:   not rotate_object $PORTLAND_HIDEOUT_DOOR from_angle 0.0 to 10.0 collision_check 0 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
end //while

02EB: restore_camera_jumpcut 
03C8: set_camera_directly_before_player 
02A3: toggle_widescreen 0 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0  
0004: $BLOB_FLAG = 1 

// *************Restart function for 8ball and the player at players hideout***************

:HIDEOUT_REACHED
if 
	0038:   $FLAG_REACHED_HIDEOUT == 1
then 
	02A3: toggle_widescreen 1 
	01B4: set_player $PLAYER_CHAR controllable 0 
	01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
	0171: set_player $PLAYER_CHAR z_angle_to 90.0 
	0395: clear_area 1 at 868.625 -311.6875 range 8.25 1.0 
	if 
		0339:   objects_in_cube 870.375 -309.875 6.0 to 865.1875 -314.6875 12.0 flags 0 1 1 1 1 
	then
		015F: set_camera_position 848.25 -295.25 19.125 0.0 rotation 0.0 0.0 //high camera that points to the water tower
		0160: point_camera 849.0625 -295.75 19.125 switchstyle JUMP_CUT
	else
		015F: set_camera_position 868.625 -311.6875 8.25 0.0 rotation 0.0 0.0 //low new camera that points to the save house
		0160: point_camera 869.5625 -311.5 8.5 switchstyle JUMP_CUT
	end
	0177: set_object $PORTLAND_HIDEOUT_DOOR z_angle_to 0.0 
	023C: load_special_actor 'EIGHT2' as 1 
	0247: request_model #KURUMA 
	038B: load_all_models_now 
	009A: $EIGHTBALL = create_char PEDTYPE_SPECIAL model #SPECIAL01 at 884.25 -309.1875 7.562
	0245: set_actor $EIGHTBALL walk_style_to ANIM_GANG2_PED
	0173: set_actor $EIGHTBALL z_angle_to 90.0 
	01ED: clear_actor $EIGHTBALL threat_search 
	00A5: $CAR_EIGHTBALL = create_car #KURUMA at $CAR_8BALL_X $CAR_8BALL_Y $CAR_8BALL_Z
	0175: set_car $CAR_EIGHTBALL z_angle_to $CAR_8BALL_HEADING 
	0229: set_car $CAR_EIGHTBALL color_to $CAR_COLOUR1_8BALL $CAR_COLOUR2_8BALL 
	01B7: release_weather 
	01D5: actor $PLAYER_ACTOR go_to_and_drive_car $CAR_EIGHTBALL 
	01D4: actor $EIGHTBALL go_to_car $CAR_EIGHTBALL and_enter_it_as_a_passenger 
	while true
		if or
			80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
			80DB:   not is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL
		jf break
		wait 0 ms
		gosub @CHECK_EIGHT_STATUS_EIGHTBALL
		gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	end
	039E: set_char_cant_be_dragged_out $EIGHTBALL to 1 
	02EB: restore_camera_jumpcut 
	03C8: set_camera_directly_before_player 
	02A3: toggle_widescreen 0 
	01B4: set_player $PLAYER_CHAR controllable 1 
	01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0
end
// **************************************end of the restart stuff***************************

if 
	$FLAG_REACHED_HIDEOUT == 0
then
	03AE: remove_objects_from_cube 804.0 -948.0 30.0 to 765.125 -924.3125 50.0 
	018E: stop_sound $FIRE_SOUND_8BALL 
	0108: destroy_object $BROKEN_BRIDGE_REMAINS 
	0108: destroy_object $BROKEN_BRIDGE_POLICE_CARS
	if
		0038:   $DEBUGUNLOCKISLANDS == 0
	then 
		03B6: replace_model_at 1027.25 -933.75 15.0 radius 50.0 from #LOD_LAND014 to #INDHELIX_BARRIER 
		0363: toggle_model_render_at 1027.25 -933.75 15.0 radius 50.0 object #INDHELIX_BARRIER 1 
	end
	0004: $FLAG_REACHED_HIDEOUT = 1 
end

016E: override_next_restart at 883.5 -308.1875 7.5625 heading 90.0 // Players hideout
gosub @CHECK_EIGHT_STATUS_EIGHTBALL

018A: $RADAR_BLIP_COORD2_EIGHTBALL = create_checkpoint_at 906.1875 -426.0 -100.0 //Luigis blip
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 2
03CF: load_wav 'LIB_B'

while 83D0:   not wav_loaded 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL
end

03D1: play_wav 
00BC: print_now 'EBAL_D' duration 5000 ms flag 1  // I know a guy, he's connected, his name's Luigi.
wait 2000 ms 
gosub @CHECK_EIGHT_STATUS_EIGHTBALL
gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL

00BC: print_now 'EBAL_D1' duration 7000 ms flag 1  // Me an' him go back so I could probably get you some work. C'mon lets head over there.

//waiting for the player to got to Luigi's

while 83D2:   not wav_ended 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL
end

03D5: remove_text 'EBAL_D'  // I know a guy, he's connected, his name's Luigi.
03D5: remove_text 'EBAL_D1'  // Me an' him go back so I could probably get you some work. C'mon lets head over there.

// waiting for the player to get to luigi's

03CF: load_wav 'LIB_C' 

while true
	if or
		81A0:   not player $PLAYER_CHAR stopped $BLOB_FLAG 903.75 -420.1875 14.0 908.25 -431.0625 18.0 
		81AA:   not actor $EIGHTBALL stopped 0 903.75 -420.1875 14.0 908.25 -431.0625 18.0 
		80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
		80DB:   not is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL 
		83D0:   not wav_loaded 
	jf break
	wait 0 
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	gosub @CHECK_IN_VEHICLE_STATUS_EIGHTBALL
	gosub @CHECK_MARKER_HELP2_STATUS_EIGHTBALL
end

03E6: remove_text_box 
0164: disable_marker $RADAR_BLIP_COORD2_EIGHTBALL 
02A3: toggle_widescreen 1 
0110: clear_player $PLAYER_CHAR wanted_level 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
03BF: set_player $PLAYER_CHAR ignored_by_everyone_to 1 
01B4: set_player $PLAYER_CHAR controllable 0 
0395: clear_area 1 at 887.375 -417.25 range 13.875 10.0 
01F5: $PLAYER_ACTOR = create_emulated_actor_from_player $PLAYER_CHAR 
01ED: clear_actor $PLAYER_ACTOR threat_search 
01D3: actor $PLAYER_ACTOR leave_car $CAR_EIGHTBALL 
01D3: actor $EIGHTBALL leave_car $CAR_EIGHTBALL 
03D1: play_wav 
00BC: print_now 'EBAL_G' duration 7000 ms flag 1  // This is Luigi's club. Let's go round the back and use the service door.

while true
	if or
		00DB:   is_char_in_car $PLAYER_ACTOR car $CAR_EIGHTBALL 
		00DB:   is_char_in_car $EIGHTBALL car $CAR_EIGHTBALL 
	jf break
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE1_STATUS_EIGHTBALL
	if
		03D2:   wav_ended 
	then
		03D5: remove_text 'EBAL_G'  // This is Luigi's club. Let's go round the back and use the service door.
	end
end

01C3: remove_references_to_car $CAR_EIGHTBALL 
0211: actor $PLAYER_ACTOR walk_to 897.0625 -426.25 
0211: actor $EIGHTBALL walk_to 897.25 -424.5625

while 83D2:   not wav_ended 
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
end

03D5: remove_text 'EBAL_G'  // This is Luigi's club. Let's go round the back and use the service door.
03BF: set_player $PLAYER_CHAR ignored_by_everyone_to 0 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
039E: set_char_cant_be_dragged_out $EIGHTBALL to 0 
03E6: remove_text_box 

// ****************************Player and eightball cut-scene at luigi's********************

01BD: $PRE_CUTSCENE_START_TIME = current_time_in_ms 
0004: $PRE_CUTSCENE_ELAPSED_TIME = 0  

while true
	if and
		83EE:   not player $PLAYER_CHAR controllable 
		001A:   5000 > $PRE_CUTSCENE_ELAPSED_TIME  //	if player is not in control after 5 secs do the cutscene anyway
	jf break
	wait 0 ms
	gosub @CHECK_EIGHT_STATUS_EIGHTBALL
	01BD: $PRE_CUTSCENE_CURRENT_TIME = current_time_in_ms 
	0084: $PRE_CUTSCENE_ELAPSED_TIME = $PRE_CUTSCENE_CURRENT_TIME 
	0060: $PRE_CUTSCENE_ELAPSED_TIME -= $PRE_CUTSCENE_START_TIME 
end

03EF: player $PLAYER_CHAR make_safe 
0169: set_fade_color 0 0 0 
016A: fade 0 for 1500 ms 
03AF: set_streaming 0 
00BA: print_big 'LM1' duration 15000 ms style 2  // 'LUIGI'S GIRLS'
0247: request_model #INDHIBUILD3 
0247: request_model #LUIGICLUBOUT 
0247: request_model #LUIGIINEERCLUB 
023C: load_special_actor 'MICKY' as 2 
023C: load_special_actor 'LUIGI' as 3 
02F3: load_object #CUTOBJ01 'LUDOOR' 
02F3: load_object #CUTOBJ02 'MICKYH' 
02F3: load_object #CUTOBJ03 'EIGHTH' 
02F3: load_object #CUTOBJ04 'LUIGIH' 
02F3: load_object #CUTOBJ05 'PLAYERH' 
while fading 
	wait 0 ms
end
038B: load_all_models_now 
03DE: set_ped_density_multiplier 0.0 
042B: clear_peds_from_cube 926.5 -471.6875 1.0 830.75 -257.9375 25.0 
if
	8118:   not actor $EIGHTBALL dead 
then
	0192: set_actor $EIGHTBALL objective_to_stand_still
end

0192: set_actor $PLAYER_ACTOR objective_to_stand_still 

// Cutscene stuff

while true
	if or
		823D:   not special_actor 2 loaded 
		823D:   not special_actor 3 loaded 
		8248:   not model #CUTOBJ01 available 
		8248:   not model #CUTOBJ02 available 
	jf break
	wait 0 ms
end

while true
	if or
		8248:   not model #CUTOBJ03 available 
		8248:   not model #CUTOBJ04 available 
		8248:   not model #CUTOBJ05 available 
		8248:   not model #INDHIBUILD3 available 
		8248:   not model #LUIGICLUBOUT available 
		8248:   not model #LUIGIINEERCLUB available 
	jf break
	wait 0 ms
end

009B: delete_char $EIGHTBALL 
0363: toggle_model_render_at 890.875 -416.875 15.0 radius 6.0 object #BACKDOOR 0 
02E4: load_cutscene_data 'L1_LG' 
0244: set_cutscene_pos 900.75 -427.5 13.8125 
02E5: $CUTSCENE_PLAYER = create_cutscene_object #NULL 
02E6: set_cutscene_anim $CUTSCENE_PLAYER 'PLAYER' 
02E5: $CUTSCENE_MICKY = create_cutscene_object #SPECIAL02 
02E6: set_cutscene_anim $CUTSCENE_MICKY 'MICKY' 
02E5: $CUTSCENE_EIGHT = create_cutscene_object #SPECIAL01 
02E6: set_cutscene_anim $CUTSCENE_EIGHT 'EIGHT2' 
02E5: $CUTSCENE_LUIGI = create_cutscene_object #SPECIAL03 
02E6: set_cutscene_anim $CUTSCENE_LUIGI 'LUIGI' 
02F4: create_cutscene_actor $MICKY_CSHEAD from_head #CUTOBJ02 and_body $CUTSCENE_MICKY 
02F5: set_head_anim $MICKY_CSHEAD 'MICKY' 
02F4: create_cutscene_actor $EIGHT_CSHEAD from_head #CUTOBJ03 and_body $CUTSCENE_EIGHT 
02F5: set_head_anim $EIGHT_CSHEAD 'EIGHT' 
02F4: create_cutscene_actor $LUIGI_CSHEAD from_head #CUTOBJ04 and_body $CUTSCENE_LUIGI 
02F5: set_head_anim $LUIGI_CSHEAD 'LUIGI' 
02F4: create_cutscene_actor $PLAYER_CSHEAD from_head #CUTOBJ05 and_body $CUTSCENE_PLAYER 
02F5: set_head_anim $PLAYER_CSHEAD 'PLAYER' 
02E5: $CS_LUDOOR = create_cutscene_object #CUTOBJ01 
02E6: set_cutscene_anim $CS_LUDOOR 'LUDOOR' 
0395: clear_area 1 at 896.5625 -426.1875 range 13.875 1.0 
0055: set_player_coordinates $PLAYER_CHAR to 896.5625 -426.1875 13.875 
0171: set_player $PLAYER_CHAR z_angle_to 270.0 
0006: 16@ = 0 
while 001B:   3500 > 16@
	wait 0 ms
end 
016A: fade 1 for 1500 ms 
0395: clear_area 1 at 887.375 -417.25 range 13.875 10.0 // This should get rid of anything in the alleway
0395: clear_area 1 at 892.75 -425.5 range 13.875 3.0 
0395: clear_area 1 at 896.25 -425.5625 range 13.75 3.0 
0395: clear_area 1 at 899.6875 -425.6875 range 14.0 0.5 
03AD: set_rubbish 0 
02E7: start_cutscene 
02E8: $CUT_SCENE_TIME = cutscenetime 
0336: set_player $PLAYER_CHAR visible 0

// Displays cutscene text

while 11165 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_H' duration 10000 ms flag 1  // Wait here man while I go in and talk to Luigi.
while 13416 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
03D5: remove_text 'EBAL_H'  // Wait here man while I go in and talk to Luigi.
//00BC: text_highpriority 'EBAL_H' duration 10000 ms flag 1 //"Da boss will be out to see you shortly..."
while 30834 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_J' duration 10000 ms flag 1  // 8-Ball's got some business up stairs.
while 33186 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_K' duration 10000 ms flag 1  // Maybe you can do me a favor.
while 35235 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_L' duration 10000 ms flag 1  // One of my girls needs a ride so grab a car and pick up Misty from the clinic. Then bring her back here.
while 41551 > $CUT_SCENE_TIME
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_M' duration 10000 ms flag 1   // Remember no one messes with my girls!
while 001A:   45634 > $CUT_SCENE_TIME  
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_N' duration 10000 ms flag 1   // So keep your hands on the wheel!
while 001A:   47560 > $CUT_SCENE_TIME  
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
00BC: text_highpriority 'EBAL_O' duration 10000 ms flag 1   // If you don't mess this up maybe there be more work for you. Now get outta here!
while 001A:   51911 > $CUT_SCENE_TIME  
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
03D5: remove_text 'EBAL_O'   // If you don't mess this up maybe there be more work for you. Now get outta here!
while 001A:   52500 > $CUT_SCENE_TIME  
	wait 0 ms
	02E8: $CUT_SCENE_TIME = cutscenetime 
end
016A: fade 0 for 1500 ms
while 82E9:   not cutscene_reached_end 
	wait 0 ms
end 

00BE: clear_prints
while fading 
	wait 0 ms
end
02EA: end_cutscene 
0373: set_camera_directly_behind_player 
0001: wait 500 ms 
03AF: set_streaming 1 
016A: fade 1 for 1500 ms 
03AD: set_rubbish 1 
03CB: load_scene 920.25 -425.375 15.0 
0363: toggle_model_render_at 890.875 -416.875 15.0 radius 6.0 object #BACKDOOR 1 
0296: unload_special_actor 1 
0296: unload_special_actor 2 
0296: unload_special_actor 3 
0249: release_model #CUTOBJ01 
0249: release_model #CUTOBJ02 
0249: release_model #CUTOBJ03 
0249: release_model #CUTOBJ04 
0249: release_model #CUTOBJ05 
0249: release_model #INDHIBUILD3 
0249: release_model #LUIGICLUBOUT 
0249: release_model #LUIGIINEERCLUB 
01B4: set_player $PLAYER_CHAR controllable 1 
03DE: set_ped_density_multiplier 1.0 

// *****************************************END OF CUTSCENE*********************************

// *****************************************LUIGI'S GIRLS***********************************

023C: load_special_actor 'MISTY' as 2 
00BC: print_now 'EBAL_5' duration 5000 ms flag 1  // ~g~Get a vehicle!

// Waiting for the player to be in a car

while 823D:   not special_actor 2 loaded 
	wait 0 ms
end

// Creates the first girl

009A: $GIRL1_LM1 = create_char PEDTYPE_SPECIAL model #SPECIAL02 at 1144.563 -592.75 13.875
01ED: clear_actor $GIRL1_LM1 threat_search 
0173: set_actor $GIRL1_LM1 z_angle_to 90.0 
0245: set_actor $GIRL1_LM1 walk_style_to ANIM_SEXY_WOMANPED
0187: $RADAR_BLIP_PED1_LM1 = create_marker_above_actor $GIRL1_LM1 
0004: $FLAG_BLIP_ON_GIRL1_LM1 = 1 
03CF: load_wav 'LIB_D' 
while true
	if or
		80E0:   not is_player_in_any_car $PLAYER_CHAR 
		83D0:   not wav_loaded 
	jf break
	wait 0 ms
	gosub @CHECK_MISTY_STATUS_EIGHTBALL
	if 
		00E0:   is_player_in_any_car $PLAYER_CHAR  
	then 
		00DA: $CAR_LM1 = store_car_player_is_in $PLAYER_CHAR
	end
end

03E6: remove_text_box 
03E5: text_box 'RADIO_A'  // Press the ~h~~k~~VEHICLE_CHANGE_RADIO_STATION~ button~w~ to cycle through the ~h~radio stations.

00BC: print_now 'EBAL_6' duration 5000 ms flag 1  // ~g~Pick up Misty!


// Waiting for the player and the girls all to be in the one car

while 0038:   $FLAG_GIRL1_IN_CAR_LM1 == 0 
	wait 0 ms
	if
		0038:   $FLAG_GIRL_IN_GROUP_LM1 == 0
	then
		gosub @CHECK_MISTY_STATUS_EIGHTBALL
		if
			0038:   $FLAG_MISTY_STOP == 0 
		then
			if
				00EB:   player $PLAYER_CHAR 0 $GIRL1_LM1 radius 20.0 20.0
			then
				03E5: text_box 'LM1_7'  // Stop the vehicle next to Misty and allow her to enter it.
				0004: $FLAG_MISTY_STOP = 1
			end
		end
		if
			80E0:   not is_player_in_any_car $PLAYER_CHAR
		then
			if
				0038:   $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 == 0
			then
				00BC: print_now 'IN_VEH2' duration 5000 ms flag 1  // ~g~You need some wheels for this job
				if
					0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 1
				then
					0164: disable_marker $RADAR_BLIP_PED1_LM1 
					0004: $FLAG_BLIP_ON_GIRL1_LM1 = 0
				end
				0004: $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 = 1
			end
		else
			00DA: $CAR_LM1 = store_car_player_is_in $PLAYER_CHAR
			if
				0038:   $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 == 1
			then
				00BC: print_now 'EBAL_6' duration 5000 ms flag 1  // ~g~Pick up Misty!
				if
					0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 0
				then
					0187: $RADAR_BLIP_PED1_LM1 = create_marker_above_actor $GIRL1_LM1 
					0004: $FLAG_BLIP_ON_GIRL1_LM1 = 1
				end
				0004: $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 = 0
			end
		end
		if
			00E0:   is_player_in_any_car $PLAYER_CHAR
		then
			00DA: $CAR_LM1 = store_car_player_is_in $PLAYER_CHAR
			if and
				00EB:   player $PLAYER_CHAR 0 $GIRL1_LM1 radius 8.0 8.0
				029F:   player $PLAYER_CHAR stopped
			then
				01D4: actor $GIRL1_LM1 go_to_car $CAR_LM1 and_enter_it_as_a_passenger
				while true
					if or
						80DB:   not is_char_in_car $GIRL1_LM1 car $CAR_LM1 
						80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_LM1
					jf break
					wait 0 ms
					gosub @CHECK_MISTY_STATUS_EIGHTBALL
					gosub @CHECK_VEHICLE2_STATUS_EIGHTBALL
					if
						00DB:   is_char_in_car $GIRL1_LM1 car $CAR_LM1
					then
						039E: set_char_cant_be_dragged_out $GIRL1_LM1 to 1
					end
					if
						80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_LM1
					then
						if
							0038:   $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 == 0 
						then
							00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
							if
								0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 1
							then
								0164: disable_marker $RADAR_BLIP_PED1_LM1 
								0186: $RADAR_BLIP_CAR1_LM1 = create_marker_above_car $CAR_LM1 
								0004: $FLAG_BLIP_ON_GIRL1_LM1 = 0 
							end
							0004: $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 = 1 
						end
					else
						if
							0038:   $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 == 1 
						then
							00BC: print_now 'EBAL_6' duration 5000 ms flag 1  // ~g~Pick up Misty!
							if
								0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 0
							then
								0187: $RADAR_BLIP_PED1_LM1 = create_marker_above_actor $GIRL1_LM1 
								0164: disable_marker $RADAR_BLIP_CAR1_LM1 
								0004: $FLAG_BLIP_ON_GIRL1_LM1 = 1
							end
							0004: $FLAG_PLAYER_HAD_VEHICLE_MESSAGE_LM1 = 0 
						end
					end
				end //while
				0164: disable_marker $RADAR_BLIP_PED1_LM1 
				0164: disable_marker $RADAR_BLIP_CAR1_LM1 
				0004: $FLAG_BLIP_ON_GIRL1_LM1 = 0 
				0004: $FLAG_GIRL_IN_GROUP_LM1 = 1 
			end
		end
	end
	if
		0038:   $FLAG_GIRL_IN_GROUP_LM1 == 1 
	then
		gosub @CHECK_MISTY_STATUS_EIGHTBALL
		gosub @CHECK_VEHICLE2_STATUS_EIGHTBALL
		if
			80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_LM1 
		then
			if
				0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_LM1 == 0
			then
				00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
				0186: $RADAR_BLIP_CAR1_LM1 = create_marker_above_car $CAR_LM1 
				0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_LM1 = 1 
			end
		else
			if
				00DB:   is_char_in_car $GIRL1_LM1 car $CAR_LM1
			then
				00BC: print_now 'LM1_9' duration 10000 ms flag 1  // Hi I'm Misty.
				03D1: play_wav 
				039E: set_char_cant_be_dragged_out $GIRL1_LM1 to 0 
				0004: $FLAG_GIRL1_IN_CAR_LM1 = 1
			end
			if
				0038:   $FLAG_PLAYER_HAD_CAR_MESSAGE_LM1 == 1
			then
				0164: disable_marker $RADAR_BLIP_CAR1_LM1 
				0004: $FLAG_PLAYER_HAD_CAR_MESSAGE_LM1 = 0
			end
		end
	end
end //while

0164: disable_marker $RADAR_BLIP_PED1_LM1 
01DF: tie_actor $GIRL1_LM1 to_player $PLAYER_CHAR

while 83D2:   not wav_ended
	wait 0 ms
	gosub @CHECK_MISTY_STATUS_EIGHTBALL
	gosub @CHECK_MISTY_RANGE_STATUS_EIGHTBALL
	gosub @CHECK_VEHICLE2_STATUS_EIGHTBALL
end //while
03D5: remove_text 'LM1_9'  // Hi I'm Misty.
00BC: print_now 'LM1_2' duration 7000 ms flag 1  // ~g~Take Misty to Luigi's Club.
018A: $RADAR_BLIP_COORD1_LM1 = create_checkpoint_at 906.1875 -426.0 -100.0 
0004: $CURRENT_STEP_FOR_BLIP_MANIPULATION = 3
0004: $BLOB_FLAG = 1 
03E5: text_box 'LOOK_A'  // Press and hold the ~h~~k~~VEHICLE_LOOKLEFT~ button ~w~or the ~h~~k~~VEHICLE_LOOKRIGHT~ button~w~ to look ~h~left~w~ or ~h~right~w~ while in a vehicle. Press both to look ~h~behind~w~.
0006: 16@ = 0 

// waiting for the player to get to luigi's

while true
	if or
		81A8:   not actor $GIRL1_LM1 stopped $BLOB_FLAG 903.75 -420.1875 14.0 908.25 -431.0625 18.0 
		819E:   not player $PLAYER_CHAR stopped 0 903.75 -420.1875 14.0 908.25 -431.0625 18.0 
	jf break
	wait 0 ms
	if
		0038:   $FLAG_PLAYER_HAD_CAMERA_MESSAGE_8BALL == 0
	then
		if
			0019:   16@ > 10000
		then
			03E5: text_box 'CAM_A'  // Press the ~h~~k~~CAMERA_CHANGE_VIEW_ALL_SITUATIONS~ button~w~ to change ~h~camera ~w~modes when on foot or in a vehicle.
			0004: $FLAG_PLAYER_HAD_CAMERA_MESSAGE_8BALL = 1
		end
	end
	gosub @CHECK_MISTY_STATUS_EIGHTBALL
	gosub @CHECK_MISTY_RANGE_STATUS_EIGHTBALL
end



0164: disable_marker $RADAR_BLIP_COORD1_LM1 
01E0: clear_leader $GIRL1_LM1 
// *********************************MISTY CUT AT END****************************************
02A3: toggle_widescreen 1 
01B4: set_player $PLAYER_CHAR controllable 0 
0110: clear_player $PLAYER_CHAR wanted_level 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 1 
03BF: set_player $PLAYER_CHAR ignored_by_everyone_to 1 
0395: clear_area 1 at 887.375 -417.25 range 13.875 10.0 // This should get rid of any stuff for the cut-scene
0247: request_model #INDHIBUILD3 
0247: request_model #LUIGICLUBOUT 
0247: request_model #LUIGIINEERCLUB 


if 
	00DF:   is_char_in_any_car $GIRL1_LM1   
then
	039E: set_char_cant_be_dragged_out $GIRL1_LM1 to 0 
	00D9: $CAR_LM1 = store_car_char_is_in $GIRL1_LM1
	01D3: actor $GIRL1_LM1 leave_car $CAR_LM1 
	while 00DF:   is_char_in_any_car $GIRL1_LM1 
		wait 0 ms
		gosub @CHECK_MISTY_STATUS_EIGHTBALL
	end
end

while true
	if or
		8248:   not model #INDHIBUILD3 available 
		8248:   not model #LUIGICLUBOUT available 
		8248:   not model #LUIGIINEERCLUB available 
	jf break
	wait 0 ms
	gosub @CHECK_MISTY_STATUS_EIGHTBALL
end

// Misty walks to the top of the alleyway
0211: actor $GIRL1_LM1 walk_to 900.125 -425.375 
0006: 17@ = 0
while 001B:   1000 > 17@ 
	wait 0 ms
	gosub @CHECK_MISTY_STATUS_EIGHTBALL
end

00A1: set_char_coordinates $GIRL1_LM1 to 898.875 -425.75 13.875 
0173: set_actor $GIRL1_LM1 z_angle_to 90.0 
0211: actor $GIRL1_LM1 walk_to 887.0625 -425.1875 
02A3: toggle_widescreen 1 
015F: set_camera_position 882.5625 -425.5625 14.375 0.0 rotation 0.0 0.0 
0160: point_camera 890.1875 -421.0625 15.0 switchstyle JUMP_CUT
01E3: text_1number_styled 'M_PASS' number 1500 duration 5000 ms style 1  // MISSION PASSED! $~1~
0109: player $PLAYER_CHAR money += 1500 
0394: play_mission_passed_music 1 
0006: 17@ = 0 
while 5000 > 17@
	wait 0 ms
end
0169: set_fade_color 0 0 0 
016A: fade 0 for 1000 ms
while fading
	wait 0 ms
end
wait 0 ms
0373: set_camera_directly_behind_player 
wait 0 ms
02A3: toggle_widescreen 0 
wait 0 ms
02EB: restore_camera_jumpcut 
wait 750 ms 
016A: fade 1 for 250 ms 
01B4: set_player $PLAYER_CHAR controllable 1 
01F7: set_player $PLAYER_CHAR ignored_by_cops_state_to 0 
03BF: set_player $PLAYER_CHAR ignored_by_everyone_to 0

goto @MISSION_PASSED_EIGHTBALL


////////////////////////////////////////////

:CHECK_EIGHT_STATUS_EIGHTBALL
if
	0118:   actor $EIGHTBALL dead 
then
	00BC: print_now 'EBAL_4' duration 5000 ms flag 1  // ~r~8-Ball's dead!
	goto @MISSION_FAILED_EIGHTBALL
end
return

////////////////////////////////////////////

:CHECK_VEHICLE1_STATUS_EIGHTBALL
if
	0119:   car $CAR_EIGHTBALL wrecked
then
	00BC: print_now 'WRECKED' duration 5000 ms flag 1   // ~r~The vehicle is wrecked!
	goto @MISSION_FAILED_EIGHTBALL
end
if and
	01F4:   car $CAR_EIGHTBALL flipped
	01C1:   car $CAR_EIGHTBALL stopped
then
	00BC: print_now 'UPSIDE' duration 5000 ms flag 1   // ~r~You flipped your wheels!
	goto @MISSION_FAILED_EIGHTBALL
end
return

////////////////////////////////////////////

:CHECK_IN_VEHICLE_STATUS_EIGHTBALL
if
	80DC:   not is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
then
	0004: $BLOB_FLAG = 0
	if
		0038:   $FLAG_CAR_MESSAGE_8BALL == 0
	then
		00BC: print_now 'IN_VEH' duration 5000 ms flag 1  // ~g~Hey! Get back in the vehicle!
		if
			0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 1
		then
			0164: disable_marker $RADAR_BLIP_COORD1_EIGHTBALL 
		else
			0164: disable_marker $RADAR_BLIP_COORD2_EIGHTBALL 
		end
		0186: $RADAR_BLIP_CAR1_EIGHTBALL = create_marker_above_car $CAR_EIGHTBALL 
		0004: $FLAG_CAR_MESSAGE_8BALL = 1
	end
else
	0004: $BLOB_FLAG = 1
	if
		0038:   $FLAG_CAR_MESSAGE_8BALL == 1
	then
		0164: disable_marker $RADAR_BLIP_CAR1_EIGHTBALL 
		if
			0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 1
		then
			018A: $RADAR_BLIP_COORD1_EIGHTBALL = create_checkpoint_at 875.0 -309.0 -100.0
		else
			018A: $RADAR_BLIP_COORD2_EIGHTBALL = create_checkpoint_at 906.1875 -426.0 -100.0  
		end
		0004: $FLAG_CAR_MESSAGE_8BALL = 0 
	end
end
return



////////////////////////////////////////////

// The first 2 minutes of the game, prevent the player from getting a wanted level.
:CHECK_CLEAR_PLAYER_WANTED_LEVEL
if 
	120000 > 17@ // 2 min
then
	0110: clear_player $PLAYER_CHAR wanted_level
end
return

////////////////////////////////////////////

:CHECK_MARKER_HELP1_STATUS_EIGHTBALL
if and
	00E5:   player $PLAYER_CHAR 0 875.0 -309.0 radius 20.0 20.0 
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
	0038:   $FLAG_HELP_8BALL1 == 0 
then
	03E5: text_box 'HELP1'  // Stop in the center of the blue marker.
	0004: $FLAG_HELP_8BALL1 = 1 
end
return

////////////////////////////////////////////

:CHECK_MARKER_HELP2_STATUS_EIGHTBALL
if and
	00E5:   player $PLAYER_CHAR 0 902.75 -425.5625 radius 15.0 15.0 
	00DC:   is_player_in_car $PLAYER_CHAR car $CAR_EIGHTBALL 
	0038:   $FLAG_HELP_8BALL2 == 0 
then
	03E5: text_box 'HELP1'  // Stop in the center of the blue marker.
	0004: $FLAG_HELP_8BALL2 = 1 
end
return

////////////////////////////////////////////

:CHECK_MISTY_STATUS_EIGHTBALL
if
	0118:   actor $GIRL1_LM1 dead 
then
	00BC: text_highpriority 'MISTY1' duration 5000 ms flag 1   // ~r~Misty is morgue-meat!
	goto @MISSION_FAILED_EIGHTBALL 
end
return

////////////////////////////////////////////

:CHECK_MISTY_RANGE_STATUS_EIGHTBALL
if and
	8320:   not actor $GIRL1_LM1 in_range_of_player $PLAYER_CHAR 
	0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 0 
then
	00BC: print_now 'HEY4' duration 5000 ms flag 1  // ~g~Lose Misty and Luigi will lose your face! Go and get her!
	0187: $RADAR_BLIP_PED1_LM1 = create_marker_above_actor $GIRL1_LM1 
	0004: $FLAG_BLIP_ON_GIRL1_LM1 = 1
	if
		0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 3
	then
		0164: disable_marker $RADAR_BLIP_COORD1_LM1 
		0004: $BLOB_FLAG = 0 
	end
end
if and
	00E9:   player $PLAYER_CHAR 0 $GIRL1_LM1 radius 8.0 8.0 
	0038:   $FLAG_BLIP_ON_GIRL1_LM1 == 1
then
	01DF: tie_actor $GIRL1_LM1 to_player $PLAYER_CHAR 
	0164: disable_marker $RADAR_BLIP_PED1_LM1 
	0004: $FLAG_BLIP_ON_GIRL1_LM1 = 0
	if
		0038:   $CURRENT_STEP_FOR_BLIP_MANIPULATION == 3
	then
		00BC: print_now 'LM1_2' duration 7000 ms flag 1  // ~g~Take Misty to Luigi's Club.
		018A: $RADAR_BLIP_COORD1_LM1 = create_checkpoint_at 906.1875 -426.0 -100.0 
		0004: $BLOB_FLAG = 1 
	end
end
return

////////////////////////////////////////////

:CHECK_VEHICLE2_STATUS_EIGHTBALL
if
	0119:   car $CAR_LM1 wrecked 
then
	if
		0118:   actor $GIRL1_LM1 dead
	then
		00BC: print_now 'MISTY1' duration 5000 ms flag 1  // ~r~Misty is morgue-meat!
		goto @MISSION_FAILED_EIGHTBALL
	else
		00BC: print_now 'WRECKED' duration 5000 ms flag 1  // ~r~The vehicle is wrecked!
		goto @MISSION_FAILED_EIGHTBALL
	end
end
return

////////////////////////////////////////////

// Mission 8ball failed 

:MISSION_FAILED_EIGHTBALL
00BA: print_big 'M_FAIL' duration 5000 ms style 1  // MISSION FAILED!
0004: $FLAG_EIGHTBALL_MISSION_LAUNCHED = 0 
if 
	0038:   $FLAG_REACHED_HIDEOUT == 0 
then
	0255: set_critical_mission_restart_at 811.875 -939.9375 35.75 angle 180.0 // New bridge restart
else
	0255: set_critical_mission_restart_at 883.5 -308.1875 7.5625 angle 90.0 // Player hideout
end
00D8: mission_has_finished

while 8256:   not is_player $PLAYER_CHAR defined 
	wait 0 ms
end
goto @MISSION_EIGHTBALL_END

////////////////////////////////////////////

// mission eightball passed

:MISSION_PASSED_EIGHTBALL
01F6: cancel_override_restart 
0004: $LUIGIS_GIRLS_COMPLETED = 1 
0318: set_latest_mission_passed 'LM1'  // 'LUIGI'S GIRLS'
030C: set_mission_points += 1 
0004: $FLAG_LUIGI_MISSION1_PASSED = 1 
0110: clear_player $PLAYER_CHAR wanted_level 
02A7: $LUIGI_MISSION_MARKER = create_icon_marker_and_sphere RADAR_SPRITE_LUIGI at 892.75 -425.75 13.875 // New blip down alleyway
004F: create_thread @LUIGI_MISSION2_LOOP
004F: create_thread @BLOB_HELP_LOOP
004F: create_thread @LUIGI_MESSAGE
return 

////////////////////////////////////////////

// mission cleanup

:MISSION_CLEANUP_EIGHTBALL
0004: $ONMISSION = 0 
0004: $ON_MISSION_FOR_8BALL = 0 
03AE: remove_objects_from_cube 804.0 -948.0 30.0 to 765.125 -924.3125 50.0 
018E: stop_sound $FIRE_SOUND_8BALL 
0169: set_fade_color 0 0 0 
0249: release_model #INDHIBUILD3 
0249: release_model #LUIGICLUBOUT 
0249: release_model #LUIGIINEERCLUB 
0249: release_model #KURUMA 
0164: disable_marker $RADAR_BLIP_COORD1_EIGHTBALL 
0164: disable_marker $RADAR_BLIP_COORD2_EIGHTBALL 
0164: disable_marker $RADAR_BLIP_CAR1_EIGHTBALL 
009B: delete_char $EIGHTBALL 
009B: delete_char $GIRL1_LM1 
0164: disable_marker $RADAR_BLIP_COORD1_LM1 
0164: disable_marker $RADAR_BLIP_PED1_LM1 
0164: disable_marker $RADAR_BLIP_CAR1_LM1 
03E7: flash_hud -1 
if 
	8119:   not car $COP_CAR1_8BALL wrecked
then
	01C3: remove_references_to_car $COP_CAR1_8BALL 
end
if 
	8119:   not car $COP_CAR2_8BALL wrecked
then
	01C3: remove_references_to_car $COP_CAR2_8BALL 
end
if 
	8118:   not actor $GIRL1_LM1 dead 
then
	039E: set_char_cant_be_dragged_out $GIRL1_LM1 to 0 
end
mission_cleanup 
return 

////////////////////////////////////////////
