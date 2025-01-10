m2=${M2_REPO}
scp -P 3993 -rp ${m2}/edu/ezip/ing1/pds/xmart-zity-backend/1.0-SNAPSHOT \
			toto@172.31.249.53:.m2/repository/edu/ezip/ing1/pds/xmart-zity-backend/

scp -P 3993 -p ${HOME}/wrkspc/i₁/smart-city-by-ezip/xmart-city-backend/main-backend-server.sh	\
			toto@172.31.249.53:smart-city-by-ezip/xmart-city-backend

