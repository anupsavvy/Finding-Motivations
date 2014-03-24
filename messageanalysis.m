clear; clc; close all;

% Load file and read messages into cell

fprintf("Running K-means algorithm on message vectors\n");

X = load("message_vectors.txt");

% bring X to 2 dimensional vectors for plotting

%Xlow = pca(X,2);

%plot(Xlow(:,1),Xlow(:,2),"rx","MarkerSize",10);
%axis([-3,1,-1,1]);

total_iter = 4;
total_cost = zeros(total_iter-1,1);
iter = zeros(total_iter-1,1);

% Setting values of K and iterations here

for i = 2:total_iter

	% Output progress
	fprintf('%d-Means ... \n',i);

	max_iters = 40;

	% initializing the centroids randomnly

	initial_centroids = kMeansInitCentroids(X,i);

	% run K-means

	[centroids,idx,Jcost,clusterSize] = runkMeans(X,initial_centroids,max_iters);

	total_cost(i-1,1) = min(Jcost);

	iter(i-1,1) = i;

	if i == 3
		save("-text","./output/clusterSize.txt","clusterSize");
	    save("-text","./output/centroids.txt","centroids");
	    save("-text","./output/idx.txt","idx");
		save("-text","./output/clusters.txt","idx");
	end
end

plot(iter(:,1),total_cost(:,1));
xlabel("Number of means used");
ylabel("Minimum Jcost");