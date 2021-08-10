DELETE FROM jpaschema.ACfgFldEn;
DELETE FROM jpaschema.AltColumnTable;
DELETE FROM jpaschema.XACfgFldE;
DELETE FROM jpaschema.XAltColumnTable;
            
DELETE FROM AnnEmbedMultiTableEnt;
DELETE FROM AnnMSCMultiTableEnt;
DELETE FROM AnnMultiTableEnt;
DELETE FROM DatatypeSupPropTestEntity;
DELETE FROM DatatypeSupTestEntity;
DELETE FROM EmbeddableIdEntity;
DELETE FROM EmbeddedObjectAOEntity;
DELETE FROM EmbeddedObjectEntity;
DELETE FROM IdClassEntity;
DELETE FROM OPENJPA_SEQUENCES_TABLE;
DELETE FROM OPENJPA_SEQUENCE_TABLE;
DELETE FROM PKEntityByte;
DELETE FROM PKEntityByteWrapper;
DELETE FROM PKEntityChar;
DELETE FROM PKEntityCharacterWrapper;
DELETE FROM PKEntityInt;
DELETE FROM PKEntityIntWrapper;
DELETE FROM PKEntityJavaSqlDate;
DELETE FROM PKEntityJavaUtilDate;
DELETE FROM PKEntityLong;
DELETE FROM PKEntityLongWrapper;
DELETE FROM PKEntityShort;
DELETE FROM PKEntityShortWrapper;
DELETE FROM PKEntityString;
DELETE FROM PKGenAutoEntity;
DELETE FROM PKGenIdentityEntity;
#DELETE FROM PKGenSequenceType1Entity;
#DELETE FROM PKGenSequenceType2Entity;
DELETE FROM PKGenTableType1Entity;
DELETE FROM PKGenTableType2Entity;
DELETE FROM PKGenTableType3Entity;
DELETE FROM PKGenTableType4Entity;
DELETE FROM ReadOnlyEntity;
DELETE FROM SEC_TABLE1;
DELETE FROM SEC_TABLE2AMSC;
DELETE FROM SEC_TABLEEMB;
DELETE FROM SerialDatatypeSupPropTE;
DELETE FROM SerialDatatypeSupTE;
DELETE FROM TableIDGen4Table;
DELETE FROM TableIDGenTable;
DELETE FROM VersionedIntEntity;
DELETE FROM VersionedIntWrapperEntity;
DELETE FROM VersionedLongEntity;
DELETE FROM VersionedLongWrapperEntity;
DELETE FROM VersionedShortEntity;
DELETE FROM VersionedShortWrapperEntity;
DELETE FROM VersionedSqlTimestampEntity;
DELETE FROM XMLDatatypeSupPropTestEntity;
DELETE FROM XMLDatatypeSupTestEntity;
DELETE FROM XMLEmbeddableIdEntity;
DELETE FROM XMLEmbeddedObjectAOEntity;
DELETE FROM XMLEmbeddedObjectEntity;
DELETE FROM XMLEmbedMultiTableEnt;
DELETE FROM XMLIdClassEntity;
DELETE FROM XMLMSCMultiTableEnt;
DELETE FROM XMLMultiTableEnt;
DELETE FROM XMLPKEntityByte;
DELETE FROM XMLPKEntityByteWrapper;
DELETE FROM XMLPKEntityChar;
DELETE FROM XMLPKEntityCharacterWrapper;
DELETE FROM XMLPKEntityInt;
DELETE FROM XMLPKEntityIntWrapper;
DELETE FROM XMLPKEntityJavaSqlDate;
DELETE FROM XMLPKEntityJavaUtilDate;
DELETE FROM XMLPKEntityLong;
DELETE FROM XMLPKEntityLongWrapper;
DELETE FROM XMLPKEntityShort;
DELETE FROM XMLPKEntityShortWrapper;
DELETE FROM XMLPKEntityString;
DELETE FROM XMLPKGenAutoEntity;
DELETE FROM XMLPKGenIdentityEntity;
#DELETE FROM XMLPKGenSequenceType1Entity;
#DELETE FROM XMLPKGenSequenceType2Entity;
DELETE FROM XMLPKGenTableType1Entity;
DELETE FROM XMLPKGenTableType2Entity;
DELETE FROM XMLPKGenTableType3Entity;
DELETE FROM XMLPKGenTableType4Entity;
DELETE FROM XMLReadOnlyEntity;
DELETE FROM XMLSerialDatatypeSupPropTE;
DELETE FROM XMLSerialDatatypeSupTE;
DELETE FROM XMLTableIDGen4Table;
DELETE FROM XMLTableIDGenTable;
DELETE FROM XMLVersionedIntEntity;
DELETE FROM XMLVersionedIntWrapperEntity;
DELETE FROM XMLVersionedLongEntity;
DELETE FROM XMLVersionedLongWrapperEnt;
DELETE FROM XMLVersionedShortEntity;
DELETE FROM XMLVersionedShortWrapperEnt;
DELETE FROM XMLVersionedSqlTimestampEnt;
DELETE FROM XSEC_TABLE1;
DELETE FROM XSEC_TABLE2AMSC;
DELETE FROM XSEC_TABLEEMB;
            
              
DELETE FROM SEQUENCE;
DELETE FROM OPENJPA_SEQUENCES_TABLE;
DELETE FROM OPENJPA_SEQUENCE_TABLE;
DELETE FROM TableIDGenTable;
DELETE FROM TableIDGen4Table;
DELETE FROM XMLTableIDGen4Table;
DELETE FROM XMLTableIDGenTable;

INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN_TABLE', 0);
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('TableType2Generator', 0);
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('XMLTableType2Generator', 0);

INSERT INTO TableIDGen4Table(GEN_NAME, GEN_VAL) values ('TableType4Generator', 999);
INSERT INTO XMLTableIDGen4Table(GEN_NAME, GEN_VAL) values ('XMLTableType4Generator', 999);
INSERT INTO TableIDGenTable(GEN_NAME, GEN_VAL) values ('TableType3Generator', 0);
INSERT INTO XMLTableIDGenTable(GEN_NAME, GEN_VAL) values ('XMLTableType3Generator', 0);

            
#INSERT INTO  SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN_SEQUENCE', 0);
#INSERT INTO  SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0);
#INSERT INTO  SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN_TABLE', 0);
#INSERT INTO  SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('TableType2Generator', 0);
#INSERT INTO  SEQUENCE (SEQ_NAME, SEQ_COUNT) values ('XMLTableType2Generator', 0);

#INSERT INTO  TableIDGenTable  (GEN_NAME, GEN_VAL) values ('TableType3Generator', 0);
#INSERT INTO  TableIDGen4Table (GEN_NAME, GEN_VAL) values ('TableType4Generator', 0);

#INSERT INTO  XMLTableIDGenTable  (GEN_NAME, GEN_VAL) values ('XMLTableType3Generator', 0);
#INSERT INTO  XMLTableIDGen4Table (GEN_NAME, GEN_VAL) values ('XMLTableType4Generator', 0);