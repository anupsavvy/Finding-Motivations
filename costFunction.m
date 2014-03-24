function Jcost = costFunction(X,centroids,idx)
	[m,n] = size(X);
	Jcost = 0;
	for i = 1:m
		Jcost = Jcost + sqrt(sum((X(i,:) - centroids(idx(i,1),:)).^2));
	end
	Jcost = Jcost/m;
end