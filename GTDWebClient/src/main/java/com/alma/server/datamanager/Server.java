package com.alma.server.datamanager;

/**
 * Cette interface specifie les méthodes offertes par un serveur GTD quelconque. Ici la synchronisation n'est pas prise en compte.
 */
public interface Server extends ConnectToServer, DataManager, DataManagerNoSync {}
