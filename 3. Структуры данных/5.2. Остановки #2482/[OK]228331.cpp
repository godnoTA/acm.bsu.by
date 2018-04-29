#include<iostream>
#include<fstream>
#include<queue>
#include<stack>
//22 37

//00 58


//8 20
//9 18

//11 20(11?)
//несколько раз в маршруте остановка!
//можно вышел-вошел в тот же маршрут
//0 если совпали начало-конец

//12 23

//нет смысла два раза подряд менять маршрут!!!!
using namespace std;

struct state {
	short stop_ind;
	short route_ind;
	short step;
	short stop_before;
	short route_before;

	bool has_changed_route = false;
};

struct routeInfo {
	int stop_class;
	short next_stop_in_class_route;
	short next_stop_in_class_stop;
	short stop_before;
	short route_before;
	bool someone_is_on_the_way_to_it = false;
	routeInfo() {
		stop_class = -1;
		next_stop_in_class_route = -1;
		next_stop_in_class_stop = -1;
		stop_before = -1;
		route_before = -1;
	}
};

int main() {

	//ifstream in("in.txt");
	FILE* in = fopen("in.txt", "r");
	FILE* out = fopen("out.txt", "w");

	int start_stop;
	int end_stop;
	int num_of_stops;
	int num_of_routes;
	//in >> num_of_stops;
	fscanf_s(in, "%d", &num_of_stops);
	//in >> num_of_routes;
	fscanf_s(in, "%d", &num_of_routes);
	//in >> end_stop;
	fscanf_s(in, "%d", &end_stop);
	//in >> start_stop;
	fscanf_s(in, "%d", &start_stop);
	if (start_stop == end_stop) {
		fprintf(out, "%d", 0);
	}
	else {
		routeInfo**info = new routeInfo*[num_of_routes];
		short** first_stop_in_class = new short*[num_of_stops];
		short** last_stop_in_class = new short*[num_of_stops];
		for (int i = 0; i < num_of_stops; ++i) {
			first_stop_in_class[i] = new short[2];
			last_stop_in_class[i] = new short[2];
			first_stop_in_class[i][0] = -1;
			first_stop_in_class[i][1] = -1;
			last_stop_in_class[i][0] = -1;
			last_stop_in_class[i][1] = -1;
		}

		//read
		short* stops_in_route = new short[num_of_routes];
		for (int i = 0; i < num_of_routes; ++i) {
			stops_in_route[i];
			//in >> stops_in_route[i];
			fscanf_s(in, "%d", &stops_in_route[i]);
			info[i] = new routeInfo[stops_in_route[i]];
			for (int j = 0; j < stops_in_route[i]; ++j) {
				//in >> info[i][j].stop_class;
				fscanf_s(in, "%d", &info[i][j].stop_class);
				if (first_stop_in_class[info[i][j].stop_class - 1][0] == -1) {
					first_stop_in_class[info[i][j].stop_class - 1][0] = i;
					first_stop_in_class[info[i][j].stop_class - 1][1] = j;
				}
				else {
					short route_ind = last_stop_in_class[info[i][j].stop_class - 1][0];
					short stop_ind = last_stop_in_class[info[i][j].stop_class - 1][1];
					info[route_ind][stop_ind].next_stop_in_class_route = i;
					info[route_ind][stop_ind].next_stop_in_class_stop = j;
				}
				last_stop_in_class[info[i][j].stop_class - 1][0] = i;
				last_stop_in_class[info[i][j].stop_class - 1][1] = j;
			}
		}




		for (int i = 0; i < num_of_stops; ++i) {
			short last_route_ind = last_stop_in_class[i][0];
			short last_stop_ind = last_stop_in_class[i][1];
			short first_route_ind = first_stop_in_class[i][0];
			short first_stop_ind = first_stop_in_class[i][1];
			if (last_route_ind >= 0 && last_stop_ind >= 0) {
				info[last_route_ind][last_stop_ind].next_stop_in_class_route = first_route_ind;
				info[last_route_ind][last_stop_ind].next_stop_in_class_stop = first_stop_ind;
			}
		}


		//do
		queue<state> visited;
		short route_ind = first_stop_in_class[start_stop - 1][0];
		short stop_ind = first_stop_in_class[start_stop - 1][1];

		state visited_from_start;
		visited_from_start.route_ind = route_ind;
		visited_from_start.step = 0;
		visited_from_start.route_before = route_ind;
		visited_from_start.stop_before = stop_ind;
		if (stop_ind + 1 < stops_in_route[route_ind]) {
			visited_from_start.stop_ind = stop_ind + 1;
			if (info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before == -1 && info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_class != start_stop) {
				visited.push(visited_from_start);
				info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before = visited_from_start.stop_before;
				info[visited_from_start.route_ind][visited_from_start.stop_ind].route_before = visited_from_start.route_before;
			}
		}
		if (stop_ind - 1 >= 0) {
			visited_from_start.stop_ind = stop_ind - 1;
			if (info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before == -1 && info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_class != start_stop) {
				visited.push(visited_from_start);
				info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before = visited_from_start.stop_before;
				info[visited_from_start.route_ind][visited_from_start.stop_ind].route_before = visited_from_start.route_before;
			}
		}
		while (!(route_ind == last_stop_in_class[start_stop - 1][0] && stop_ind == last_stop_in_class[start_stop - 1][1]))
		{
			short new_route_ind = info[route_ind][stop_ind].next_stop_in_class_route;
			short new_stop_ind = info[route_ind][stop_ind].next_stop_in_class_stop;
			route_ind = new_route_ind;
			stop_ind = new_stop_ind;
			state visited_from_start;
			visited_from_start.route_ind = route_ind;
			visited_from_start.step = 0;
			visited_from_start.route_before = route_ind;
			visited_from_start.stop_before = stop_ind;
			if (stop_ind + 1 < stops_in_route[route_ind]) {
				visited_from_start.stop_ind = stop_ind + 1;
				if (info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before == -1 && info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_class != start_stop) {
					visited.push(visited_from_start);
					info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before = visited_from_start.stop_before;
					info[visited_from_start.route_ind][visited_from_start.stop_ind].route_before = visited_from_start.route_before;
				}
			}
			if (stop_ind - 1 >= 0) {
				visited_from_start.stop_ind = stop_ind - 1;
				if (info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before == -1 && info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_class != start_stop) {
					visited.push(visited_from_start);
					info[visited_from_start.route_ind][visited_from_start.stop_ind].stop_before = visited_from_start.stop_before;
					info[visited_from_start.route_ind][visited_from_start.stop_ind].route_before = visited_from_start.route_before;
				}
			}
		}


		for (int i = 0; i < num_of_stops; ++i) {
			delete[] last_stop_in_class[i];
			delete[] first_stop_in_class[i];
		}
		delete[] last_stop_in_class;
		delete[] first_stop_in_class;
		while (!visited.empty() && !(info[visited.front().route_ind][visited.front().stop_ind].stop_class == end_stop))
		{
			state last_stop = visited.front();

			short last_route_ind = last_stop.route_ind;
			int last_class = info[last_route_ind][last_stop.stop_ind].stop_class;
			visited.pop();

			state next_stop;
			next_stop.route_before = last_stop.route_ind;
			next_stop.stop_before = last_stop.stop_ind;
			if (last_stop.step == 0) {
				short cur_stop = last_stop.stop_ind;
				short cur_route = last_stop.route_ind;
				next_stop.step = 0;
				next_stop.route_ind = cur_route;
				if (cur_stop + 1 < stops_in_route[cur_route]) {
					next_stop.stop_ind = cur_stop + 1;

					if (info[next_stop.route_ind][next_stop.stop_ind].stop_before == -1) {
						visited.push(next_stop);
						info[next_stop.route_ind][next_stop.stop_ind].stop_before = next_stop.stop_before;
						info[next_stop.route_ind][next_stop.stop_ind].route_before = next_stop.route_before;
					}
				}

				if (cur_stop - 1 >= 0) {
					next_stop.stop_ind = cur_stop - 1;
					if (info[next_stop.route_ind][next_stop.stop_ind].stop_before == -1) {
						visited.push(next_stop);
						info[next_stop.route_ind][next_stop.stop_ind].stop_before = next_stop.stop_before;
						info[next_stop.route_ind][next_stop.stop_ind].route_before = next_stop.route_before;
					}
				}

				short new_stop = info[cur_route][cur_stop].next_stop_in_class_stop;
				short new_route = info[cur_route][cur_stop].next_stop_in_class_route;
				cur_stop = new_stop;
				cur_route = new_route;
				//bool flag = true;
				if (!last_stop.has_changed_route) {
					while (!(cur_stop == last_stop.stop_ind && cur_route == last_stop.route_ind)) {
						next_stop.step = 2;
						next_stop.stop_ind = cur_stop;
						next_stop.route_ind = cur_route;
						next_stop.has_changed_route = true;
						//visited.push(next_stop);
						if (info[next_stop.route_ind][next_stop.stop_ind].stop_before == -1 && !info[next_stop.route_ind][next_stop.stop_ind].someone_is_on_the_way_to_it &&
							!((cur_stop + 1< stops_in_route[cur_route] && info[cur_route][cur_stop + 1].stop_before != -1) ||
							(cur_stop + 2< stops_in_route[cur_route] && info[cur_route][cur_stop + 2].stop_before != -1) ||
								
								(cur_stop - 1 >= 0 && info[cur_route][cur_stop - 1].stop_before != -1) ||
								(cur_stop - 2 >= 0 && info[cur_route][cur_stop - 2].stop_before != -1) 
								))
						{
							visited.push(next_stop);
							info[next_stop.route_ind][next_stop.stop_ind].someone_is_on_the_way_to_it = true;
							//info[next_stop.route_ind][next_stop.stop_ind].stop_before = next_stop.stop_before;
							//info[next_stop.route_ind][next_stop.stop_ind].route_before = next_stop.route_before;


						}

						short new_stop = info[cur_route][cur_stop].next_stop_in_class_stop;
						short new_route = info[cur_route][cur_stop].next_stop_in_class_route;
						cur_stop = new_stop;
						cur_route = new_route;
					}
				}

			}
			else {
				if (last_stop.step == 2) {
					last_stop.step = 1;
					//visited.push(last_stop);
					if (info[last_stop.route_ind][last_stop.stop_ind].stop_before == -1) {
						visited.push(last_stop);
						//info[last_stop.route_ind][last_stop.stop_ind].stop_before = last_stop.stop_before;
						//info[last_stop.route_ind][last_stop.stop_ind].route_before = last_stop.route_before;
					}
				}
				else {
					last_stop.step = 0;
					if (info[last_stop.route_ind][last_stop.stop_ind].stop_before == -1) {
						visited.push(last_stop);
						info[last_stop.route_ind][last_stop.stop_ind].stop_before = last_stop.stop_before;
						info[last_stop.route_ind][last_stop.stop_ind].route_before = last_stop.route_before;
					}

				}
			}
		}




		if (visited.empty()) {
			fprintf(out, "%s", "NoWay");
		}

		else {
			stack<pair<int, short>>best_route;
			int best_cost = 0;
			state best = visited.front();
			best_cost += (int)best.step;
			info[best.route_ind][best.stop_ind].route_before = best.route_before;
			info[best.route_ind][best.stop_ind].stop_before = best.stop_before;
			short stop = best.stop_ind;
			short route = best.route_ind;
			best_route.push(make_pair(info[route][stop].stop_class, route + 1));

			while (!visited.empty()) {
				visited.pop();
			}



			while (info[route][stop].stop_class != start_stop) {
				short prev_stop = info[route][stop].stop_before;
				short prev_route = info[route][stop].route_before;
				if (info[route][stop].stop_class != info[prev_route][prev_stop].stop_class) {
					best_route.push(make_pair(info[prev_route][prev_stop].stop_class, prev_route + 1));
				}


				if (info[route][stop].stop_class == info[prev_route][prev_stop].stop_class) {
					best_cost += 3;
				}
				else
				{
					best_cost++;
				}
				route = prev_route;
				stop = prev_stop;
			}
			fprintf(out, "%d%c", best_cost, '\n');

			while (!best_route.empty())
			{
				fprintf(out, "%d%c%d%c", best_route.top().first, ' ', best_route.top().second, '\n');
				best_route.pop();
			}
		}


	}

	//system("pause");
	return 0;
}